package com.codesquad.team3.baseball.controller;

import com.codesquad.team3.baseball.dto.ResponseData;
import com.codesquad.team3.baseball.dto.Status;
import com.codesquad.team3.baseball.service.MatchingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class MatchingController {

    private MatchingService matchingService;

    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    @GetMapping("/teams")
    public ResponseEntity<ResponseData> getTeams() {
        HashMap<String, Object> teams = new HashMap<>();
        teams.put("teams", matchingService.getAllTeams());
        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, teams), HttpStatus.OK);
    }

    @GetMapping("/games")
    public ResponseEntity<ResponseData> getGames() {

        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, matchingService.getAllGames()), HttpStatus.OK);
    }

    @PatchMapping("/games/{gameId}/teams/{teamId}")
    public ResponseEntity<ResponseData> selectGame(@PathVariable int gameId,
                                                   @PathVariable int teamId) {
        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, null), HttpStatus.OK);
    }
}
