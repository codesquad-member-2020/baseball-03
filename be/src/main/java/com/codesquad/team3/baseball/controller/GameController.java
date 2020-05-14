package com.codesquad.team3.baseball.controller;

import com.codesquad.team3.baseball.dto.ExceptionDTO;
import com.codesquad.team3.baseball.dto.PitchingDTO;
import com.codesquad.team3.baseball.dto.ResponseData;
import com.codesquad.team3.baseball.dto.Status;
import com.codesquad.team3.baseball.service.GameService;
import com.codesquad.team3.baseball.service.InGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/games/{gameId}/teams/{teamId}")
public class GameController {

    @Autowired
    private GameService gameService;
    @Autowired
    private InGameService inGameService;

    @GetMapping("/init")
    public ResponseEntity<ResponseData> initGame(@PathVariable Integer gameId,
                                                 @PathVariable Integer teamId) {
        gameService.initGame(gameId, teamId);
        PitchingDTO pitchingDTO = gameService.getInitGameData(gameId, teamId);

        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, pitchingDTO), HttpStatus.OK);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ExceptionDTO> duplicateKey() {
        Map<String, String> content = new HashMap<>();
        content.put("message", "이미 초기화된 게임입니다.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDTO("ERROR", content));
    }

    @PostMapping("")
    public ResponseEntity<ResponseData> defend(@PathVariable Integer gameId,
                                               @PathVariable Integer teamId) {
        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, inGameService.getPitchingResult(gameId, teamId)), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<ResponseData> attack(@PathVariable Integer gameId,
                                               @PathVariable Integer teamId) {
        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, null), HttpStatus.OK);
    }
}
