package com.codesquad.team3.baseball.service;

import com.codesquad.team3.baseball.dao.GameDAO;
import com.codesquad.team3.baseball.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GameService {

    @Autowired
    private GameDAO gameDAO;

    @Transactional
    public void initGame(Integer gameId) {
        if(gameDAO.getHalfInningCount(gameId)==0){
            gameDAO.addHalfInning(gameId, true, 1);
            addTeamRecord(gameId);
        }
    }

    public PitchingDTO getInitGameData(Integer gameId, Integer teamId) {
        PitchingDTO pitchingDTO = new PitchingDTO.Builder()
                                    .isHome(checkHome(gameId,teamId))
                                    .team(getTeamsName(gameId))
                                    .gameScore(initScore())
                                    .halfInning(initHalfInning(gameId, teamId))
                                    .pitcher(initPitcher(teamId))
                                    .hitter(initHitter(teamId, gameId))
                                    .build();

        return pitchingDTO;
    }

    private void addTeamRecord(Integer gameId) {
        List<Integer> teams = gameDAO.findTeamIdWithGameId(gameId);
        for (Integer team:teams) {
            gameDAO.addTeamPitcherRecordRecords(gameId, team);
            gameDAO.addTeamHitterRecordRecords(gameId, team);
        }
    }

    //DB에서 홈인지 어웨이인지 확인하고 반환
    private boolean checkHome(Integer gameId, Integer teamId) {
        return gameDAO.checkHome(gameId, teamId);
    }

    private Map<String, Integer> initScore() {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("home", 0);
        scores.put("away", 0);

        return scores;
    }

    private HalfInningDTO initHalfInning(Integer gameId, Integer teamId) {
        HalfInningDTO halfInningDTO;

        if(checkHome(gameId, teamId)) {
            halfInningDTO = new HalfInningDTO(1, true, false);
        } else {
            halfInningDTO = new HalfInningDTO(1, true, true);
        }

        return halfInningDTO;
    }

    private PitcherDTO initPitcher(Integer teamId) {
        return new PitcherDTO(gameDAO.getPitcherName(teamId));
    }

    private HitterDTO initHitter(Integer teamId, Integer gameId) {
        return gameDAO.getHitter(teamId, gameId, true);
    }

    private Map<String, String> getTeamsName(Integer gameId) {
        Map<String, String> team = new HashMap<>();
        team.put("home", gameDAO.findTeamNameWithGameId(gameId, true));
        team.put("away", gameDAO.findTeamNameWithGameId(gameId, false));
        return team;
    }

}
