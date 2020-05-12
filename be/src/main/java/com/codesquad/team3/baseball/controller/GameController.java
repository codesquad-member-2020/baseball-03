package com.codesquad.team3.baseball.controller;

import com.codesquad.team3.baseball.dto.PitchingDTO;
import com.codesquad.team3.baseball.dto.ResponseData;
import com.codesquad.team3.baseball.dto.Status;
import com.codesquad.team3.baseball.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/games/{gameId}/teams/{teamId}")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/init")
    public ResponseEntity<ResponseData> initGame(@PathVariable Integer gameId,
                                                 @PathVariable Integer teamId,
                                                 HttpSession session) {
        gameService.initGame(gameId, session);
        PitchingDTO pitchingDTO = gameService.getInitGameData(gameId, teamId);

        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, pitchingDTO), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ResponseData> defend(@PathVariable Integer gameId,
                                               @PathVariable Integer teamId) {
        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, null), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<ResponseData> attack(@PathVariable Integer gameId,
                                               @PathVariable Integer teamId) {
        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, null), HttpStatus.OK);
    }
}
