package com.codesquad.team3.baseball.controller;

import com.codesquad.team3.baseball.dao.BoardDAO;
import com.codesquad.team3.baseball.dto.ResponseData;
import com.codesquad.team3.baseball.dto.Status;
import com.codesquad.team3.baseball.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/games/{gameId}/teams/{teamId}")
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/scores")
    public ResponseEntity<ResponseData> getScores(@PathVariable Integer gameId,
                                                  @PathVariable Integer teamId) {
        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, null), HttpStatus.OK);
    }

    @GetMapping("/records")
    public ResponseEntity<ResponseData> getRecords(@PathVariable Integer gameId,
                                                   @PathVariable Integer teamId) {
        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, boardService.getPlayerRecords(gameId, teamId)), HttpStatus.OK);
    }
}
