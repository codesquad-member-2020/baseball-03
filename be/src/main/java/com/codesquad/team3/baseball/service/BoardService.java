package com.codesquad.team3.baseball.service;

import com.codesquad.team3.baseball.dao.BoardDAO;
import com.codesquad.team3.baseball.dao.GameDAO;
import com.codesquad.team3.baseball.dto.BoardDTO;
import com.codesquad.team3.baseball.dto.ScoreDTO;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    private BoardDAO boardDAO;
    private GameDAO gameDAO;

    public BoardService(BoardDAO boardDAO, GameDAO gameDAO) {
        this.boardDAO = boardDAO;
        this.gameDAO = gameDAO;
    }

    public BoardDTO getScoreBoard(Integer gameId, Integer teamId) {
        ScoreDTO home = boardDAO.getTeamScores(gameId, true);
        ScoreDTO away = boardDAO.getTeamScores(gameId, false);

        return new BoardDTO(gameDAO.checkHome(gameId,teamId), home, away);
    }
}
