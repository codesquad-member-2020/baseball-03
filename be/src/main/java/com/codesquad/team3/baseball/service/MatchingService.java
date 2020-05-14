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

    public Map<String, Boolean> getSelectionResult(Integer gameId, Integer teamId) {
        Map<String,Boolean> result = new HashMap<>();
        result.put("isHome", setTeamGame(gameId, teamId));
        result.put("isReady", true);
        return result;
    }


    private boolean setTeamGame(Integer gameId, Integer teamId) {
        Map<String,Object> matchData = matchingDAO.getMatchDataWithGameIdAndTeamId(gameId,teamId);

        if(matchData.isEmpty()) {
            throw new NotFoundException();
        }

        if(matchData.get("user") != null) {
            throw new CanNotSelectException();
        }

        return (Boolean)matchData.get("is_home");
    }
}
