package com.codesquad.team3.baseball.service;

import com.codesquad.team3.baseball.dao.GameDAO;
import com.codesquad.team3.baseball.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GameService {

    @Autowired
    private GameDAO gameDAO;

    public void initGame(Integer gameId) {
        gameDAO.addHalfInning(gameId, true, 1);
        gameDAO.addTeamRecord(gameId); //해당 경기의 선수 레코드들 모두 초기화로 추가
    }

    public PitchingDTO getInitGameData(Integer gameId, Integer teamId) {
        PitchingDTO pitchingDTO = new PitchingDTO.Builder()
                                    .isHome(checkHome(gameId,teamId))
                                    .gameScore(initScore())
                                    .halfInning(initHalfInning(gameId, teamId))
                                    .pitcher(initPitcher(teamId))
                                    .hitter(initHitter(teamId, gameId))
                                    .build();

        return pitchingDTO;
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

}
