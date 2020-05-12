package com.codesquad.team3.baseball.service;

import com.codesquad.team3.baseball.dao.MatchingDAO;
import com.codesquad.team3.baseball.dto.GameDTO;
import com.codesquad.team3.baseball.dto.TeamDTO;
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
}
