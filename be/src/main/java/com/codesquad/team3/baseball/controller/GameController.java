package com.codesquad.team3.baseball.controller;

import com.codesquad.team3.baseball.dto.ResponseData;
import com.codesquad.team3.baseball.dto.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/games/{gameId}/teams/{teamId}")
public class GameController {

    @GetMapping("/init")
    public ResponseEntity<ResponseData> initGame(@PathVariable Integer gameId,
                                                 @PathVariable Integer teamId) {
        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, null), HttpStatus.OK);
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
