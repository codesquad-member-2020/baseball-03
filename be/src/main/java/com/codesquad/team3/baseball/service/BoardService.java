package com.codesquad.team3.baseball.service;

import com.codesquad.team3.baseball.dao.BoardDAO;
import com.codesquad.team3.baseball.dao.GameDAO;
import com.codesquad.team3.baseball.domain.TeamGame;
import com.codesquad.team3.baseball.dto.PlayerDTO;
import com.codesquad.team3.baseball.dto.PlayerRecordDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {

    private BoardDAO boardDAO;
    private GameDAO gameDAO;

    public BoardService(BoardDAO boardDAO, GameDAO gameDAO) {
        this.boardDAO = boardDAO;
        this.gameDAO = gameDAO;
    }

    public PlayerRecordDTO getPlayerRecords(Integer gameId, Integer teamId) {
        boolean isHome = gameDAO.checkHome(gameId, teamId);
        TeamGame oppositeTeam = boardDAO.getOppositeTeamGame(gameId, teamId);
        // 상대방 팀
        if (oppositeTeam.isHome()) {
            return new PlayerRecordDTO(isHome, getTeamData(gameId, oppositeTeam.getTeam()), getTeamData(gameId, teamId));
        }
        return new PlayerRecordDTO(isHome, getTeamData(gameId, teamId), getTeamData(gameId, oppositeTeam.getTeam()));
    }

    private Map<String, Object> getTeamData(Integer gameId, Integer teamId) {
        Map<String, Object> team = new HashMap<>();
        List<PlayerDTO> players = boardDAO.getHitters(gameId, teamId);
        team.put("totals", sum(players));
        players.add(boardDAO.getPitcher(gameId, teamId));
        team.put("players", players);
        return team;
    }

    private Map<String, Integer> sum(List<PlayerDTO> hitters) {
        Map<String, Integer> totals = new HashMap<>();
        totals.put("atBats", hitters.stream().mapToInt(PlayerDTO::getAtBats).sum());
        totals.put("hits", hitters.stream().mapToInt(PlayerDTO::getHits).sum());
        totals.put("outs", hitters.stream().mapToInt(PlayerDTO::getOuts).sum());
        return totals;
    }
}

