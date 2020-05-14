package com.codesquad.team3.baseball.service;

import com.codesquad.team3.baseball.dao.GameDAO;
import com.codesquad.team3.baseball.dao.InGameDAO;
import com.codesquad.team3.baseball.domain.*;
import com.codesquad.team3.baseball.dto.*;
import com.codesquad.team3.baseball.exception.InAppropriateRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class InGameService {

    private static final String SELECT_TIME_SESSION = "select_time";
    private InGameDAO inGameDAO;
    private GameDAO gameDAO;

    public InGameService(InGameDAO inGameDAO, GameDAO gameDAO) {
        this.inGameDAO = inGameDAO;
        this.gameDAO = gameDAO;
    }

    @Transactional
    public PitchingDTO getPitchingResult(Integer gameId, Integer teamId) {
        // * 변경될 부분
        // 추후에 세션에 isHome이 추가된다면 TeamGame이 아닌 Game 단위로 처리하면 됨
        TeamGame teamGame = inGameDAO.findTeamGameByGameIdAndTeamId(gameId, teamId);

        Game game = teamGame.getGame();
        HalfInning halfInning = game.getLastHalfInning();
        // * 예외
        // 초에 away가 수비 요청을 보낸 경우
        if ((halfInning.isTop() && !teamGame.isHome()) || (!halfInning.isTop() && teamGame.isHome())) {
            throw new InAppropriateRequest();
        }
        AtBat atBat = inGameDAO.findLastAtBatByHalfInning(halfInning.getId());
        Integer opposite = inGameDAO.findOppositeTeamIdByGameIdAndTeamId(gameId, teamId);

        Player pitcher = inGameDAO.findPitcherByTeamId(teamGame.getTeam());
        Player hitter = null;
        if (teamGame.isHome()) {
            hitter = inGameDAO.findHitterByOrder(opposite, game.getAwayBattingOrder());
        } else {
            hitter = inGameDAO.findHitterByOrder(opposite, game.getHomeBattingOrder());
        }

        Result result = hitter.hit();
        // 업데이트 결과가 true 이면 선수 교체
        boolean isNextPlayerTurn = halfInning.update(result, atBat);

        // DB 갱신
        // 1. 게임 로그 - 항상
        LocalDateTime now = LocalDateTime.now();
        inGameDAO.addGameLog(new GameLog(result, now, pitcher.getId(), hitter.getId(), atBat.getId()));

        // 2. 타석 업데이트
        if (result == Result.STRIKE || result == Result.BALL) {
            inGameDAO.updateAtBat(atBat);
        }

        // 3. 투수 레코드는 매번 업데이트
        pitcher.addPitches();
        inGameDAO.updatePitcherRecord(pitcher.getId(), gameId, teamId);

        // 4. 선수 교체
        if (isNextPlayerTurn) {
            // 4-1. 하프 이닝 업데이트 - 아웃이거나 득점인 경우 (= 선수가 교체되는 경우)
            inGameDAO.updateHalfInning(halfInning);
            // 4-2. 타자 레코드 변경
            inGameDAO.updateHitterRecord(hitter.getId(), gameId, opposite, result, atBat);
            // 4-3. 다음 선수 준비
            if (teamGame.isHome()) {
                game.nextAwayBattingOrder();
                hitter = inGameDAO.findHitterByOrder(opposite, game.getAwayBattingOrder());
            } else {
                game.nextHomeBattingOrder();
                hitter = inGameDAO.findHitterByOrder(opposite, game.getHomeBattingOrder());
            }
            // 4-4. 타석 추가
            if (!halfInning.isOver()) {
                inGameDAO.addAtBat(halfInning, hitter);
            }
            // 4-5. Game 타자 순서 변경
            inGameDAO.updateGame(game);
        }

        // 5. 하프이닝 종료된 경우
        if (halfInning.isOver()) {
            if (isGameOver(game, teamGame.isTop())) {
                return createPitchingDTO(teamGame, game, halfInning, pitcher, hitter, result, atBat);
            }
            // 5-1. 다음 하프 이닝 추가
            inGameDAO.addHalfInning(teamGame.getGame().getId(), !halfInning.isTop(), halfInning.getInning() + 1);
            // 5-2. 새로운 하프 이닝으로 대입
            halfInning = inGameDAO.findLastHalfInning(gameId);
            game.addHalfInning(halfInning);
            // 5-3. 공수 선수 교체
            pitcher = inGameDAO.findPitcherByTeamId(opposite);
            if (halfInning.isTop()) {
                hitter = inGameDAO.findHitterByOrder(teamId, game.getAwayBattingOrder());
            } else {
                hitter = inGameDAO.findHitterByOrder(teamId, game.getHomeBattingOrder());
            }
            // 5-4. 다음 팀 타석 추가
            gameDAO.addAtBat(halfInning.getId(), hitter.getId());
            atBat = inGameDAO.findLastAtBatByHalfInning(halfInning.getId());
        }

        // 6. PitchingDTO 구성
        return createPitchingDTO(teamGame, game, halfInning, pitcher, hitter, result, atBat);
    }

    public GameLog getLastGameLog(Integer gameId) {
        GameLog log;
        try {
            log = inGameDAO.findLastGameLog(gameId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return log;
    }

    @Transactional
    public PitchingDTO getLastPitching(Integer gameId, Integer teamId, GameLog log, HttpSession session) {
        TeamGame teamGame = inGameDAO.findTeamGameByGameIdAndTeamId(gameId, teamId);
        Game game = teamGame.getGame();
        HalfInning halfInning = game.getLastHalfInning();
        // * 예외
        // 초에 home이 공격 요청을 보낸 경우
        if ((halfInning.isTop() && teamGame.isHome()) || (!halfInning.isTop() && !teamGame.isHome())) {
            throw new InAppropriateRequest();
        }
        Integer opposite = inGameDAO.findOppositeTeamIdByGameIdAndTeamId(gameId, teamId);
        AtBat atBat = inGameDAO.findAtBatById(log.getAtBat());
        Player pitcher = inGameDAO.findPitcherByTeamId(opposite);
        Player hitter = null;
        if (teamGame.isHome()) {
            hitter = inGameDAO.findHitterByOrder(teamId, game.getHomeBattingOrder());
        } else {
            hitter = inGameDAO.findHitterByOrder(teamId, game.getAwayBattingOrder());
        }
        // 세션에 조회한 로그 생성 날짜 저장
        session.setAttribute(SELECT_TIME_SESSION, log.getCreateTime());
        return createPitchingDTO(teamGame, game, halfInning, pitcher, hitter, log.getResult(), atBat);
    }

    public boolean isUpdatedGameLog(HttpSession session, GameLog log) {
        LocalDateTime selectTime = (LocalDateTime)session.getAttribute(SELECT_TIME_SESSION);
        if (log == null) {
            return false;
        }
        if (selectTime == null) {
            session.setAttribute(SELECT_TIME_SESSION, log.getCreateTime());
            return true;
        }
        return selectTime.isBefore(log.getCreateTime());
    }

    private PitchingDTO createPitchingDTO(TeamGame teamGame, Game game, HalfInning halfInning, Player pitcher, Player hitter, Result result, AtBat atBat) {
        HalfInningDTO halfInningDTO = new HalfInningDTO(game.getRound(), halfInning.isTop(), teamGame.isHome() != halfInning.isTop());
        PitcherDTO pitcherDTO = new PitcherDTO(pitcher.getName(), pitcher.getPitches());
        HitterDTO hitterDTO = new HitterDTO.Builder()
                .name(hitter.getName())
                .order(hitter.getBattingOrder())
                .atBats(hitter.getAtBats())
                .hits(hitter.getHitCount())
                .outs(hitter.getOutCount())
                .avg(hitter.getAverage())
                .build();
        Map<Base, Boolean> base = getBases(halfInning);
        Map<String, Integer> gameScore = getGameScores(game);
        LogDTO logDTO = new LogDTO(result, getCounts(halfInning, atBat), result == Result.OUT || atBat.is3Strikes(), result == Result.HIT);

        return new PitchingDTO.Builder()
                .isOver(halfInning.isOver())
                .isHome(teamGame.isHome())
                .gameScore(gameScore)
                .halfInning(halfInningDTO)
                .pitcher(pitcherDTO)
                .hitter(hitterDTO)
                .log(logDTO)
                .base(base)
                .build();
    }

    private Map<Base, Boolean> getBases(HalfInning halfInning) {
        Map<Base, Boolean> base = new HashMap<>();
        base.put(Base.FIRST, halfInning.isFirstBase());
        base.put(Base.SECOND, halfInning.isSecondBase());
        base.put(Base.THIRD, halfInning.isThirdBase());
        base.put(Base.HOME, halfInning.isHomeBase());
        return base;
    }

    private Map<String, Integer> getGameScores(Game game) {
        Map<String, Integer> gameScores = new HashMap<>();
        int scores = game.getHalfInnings().stream()
                .filter((HalfInning::isTop))
                .mapToInt(HalfInning::getScore)
                .sum();
        gameScores.put("home", scores);
        scores = game.getHalfInnings().stream()
                .filter(halfInning -> !halfInning.isTop())
                .mapToInt(HalfInning::getScore)
                .sum();
        gameScores.put("away", scores);
        return gameScores;
    }

    private Map<Result, Integer> getCounts(HalfInning halfInning, AtBat atBat) {
        Map<Result, Integer> counts = new HashMap<>();
        counts.put(Result.STRIKE, atBat.getStrikeCount());
        counts.put(Result.BALL, atBat.getBallCount());
        counts.put(Result.OUT, halfInning.getOutCount());
        return counts;
    }

    private boolean isGameOver(Game game, boolean isTop) {
        final int ROUND_OVER = 9;
        final int ROUND_FINAL = 12;
        int round = game.getRound();
        Map<String, Integer> gameScores = getGameScores(game);
        int homeScore = gameScores.get("home");
        int awayScore = gameScores.get("away");

        if (round == ROUND_OVER && isTop) { // 초에 home이 이기고 있다면
            return homeScore > awayScore; // away가 이기고 있거나 동점인 경우
        }
        if ((round == ROUND_FINAL) && !isTop) {
            return true;
        }
        if (round >= ROUND_OVER && !isTop) {
            return homeScore != awayScore; // 동점이면 false
        }
        return false;
    }
}
