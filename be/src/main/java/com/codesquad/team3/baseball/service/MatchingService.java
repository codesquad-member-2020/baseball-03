package com.codesquad.team3.baseball.service;

import com.codesquad.team3.baseball.dao.MatchingDAO;
import com.codesquad.team3.baseball.dto.GameDTO;
import com.codesquad.team3.baseball.dto.TeamDTO;
import com.codesquad.team3.baseball.exception.CanNotSelectException;
import com.codesquad.team3.baseball.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatchingService {

    private MatchingDAO matchingDAO;

    public MatchingService(MatchingDAO matchingDAO) {
        this.matchingDAO = matchingDAO;
    }

    public Map<String, List<TeamDTO>> getAllTeams() {
        HashMap<String, List<TeamDTO>> teams = new HashMap<>();
        teams.put("teams", matchingDAO.selectAllTeams());
        return teams;
    }

    public List<GameDTO> getAllGames() {
        return matchingDAO.selectAllTeamGames();
    }

    public Map<String, Boolean> getSelectionResult(Integer gameId, Integer teamId, Integer userId) {
        Map<String,Boolean> result = new HashMap<>();
        result.put("isHome", setTeamGame(gameId, teamId, userId));
        result.put("isReady", checkMatching(gameId));
        return result;
    }

    private boolean setTeamGame(Integer gameId, Integer teamId, Integer userId) {
        Map<String,Object> matchData = matchingDAO.getMatchDataWithGameIdAndTeamId(gameId,teamId);

        if(matchData.isEmpty()) {
            throw new NotFoundException();
        }

        if(matchData.get("user") != null) {
            throw new CanNotSelectException();
        }

        matchingDAO.updateUserIdAtTeamGame(gameId,teamId,userId);

        return (Boolean)matchData.get("is_home");
    }

    private Boolean checkMatching(Integer gameId) {
        int teamPerGame = 2;
        int selectedTeamCount = matchingDAO.countSeletedGameWithGameId(gameId);
        return selectedTeamCount == teamPerGame;
    }

    public Map<String, Boolean> checkMatchingNow(Integer gameId) {
        Map<String,Boolean> result = new HashMap<>();
        result.put("isReady", checkMatching(gameId));
        return result;
    }
}
