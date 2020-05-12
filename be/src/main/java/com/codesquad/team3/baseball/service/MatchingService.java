package com.codesquad.team3.baseball.service;

import com.codesquad.team3.baseball.dao.MatchingDAO;
import com.codesquad.team3.baseball.dto.GameDTO;
import com.codesquad.team3.baseball.dto.TeamDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchingService {

    private MatchingDAO matchingDAO;

    public MatchingService(MatchingDAO matchingDAO) {
        this.matchingDAO = matchingDAO;
    }

    public List<TeamDTO> getAllTeams() {
        return matchingDAO.selectAllTeams();
    }

    public List<GameDTO> getAllGames() {
        return matchingDAO.selectAllTeamGames();
    }
}
