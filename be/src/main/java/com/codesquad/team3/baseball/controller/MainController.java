package com.codesquad.team3.baseball.controller;

import com.codesquad.team3.baseball.dto.ResponseData;
import com.codesquad.team3.baseball.dto.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MainController {

    @GetMapping("/teams")
    public ResponseEntity<ResponseData> getTeams() {
        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, null), HttpStatus.OK);
    }

    @GetMapping("/games")
    public ResponseEntity<ResponseData> getGames() {
        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, null), HttpStatus.OK);
    }

    @PatchMapping("/games/{gameId}/teams/{teamId}")
    public ResponseEntity<ResponseData> selectGame(@PathVariable int gameId,
                                                   @PathVariable int teamId) {
        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, null), HttpStatus.OK);
    }
}
