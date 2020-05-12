package com.codesquad.team3.baseball.controller;

import com.codesquad.team3.baseball.dao.MainDAO;
import com.codesquad.team3.baseball.dto.ResponseData;
import com.codesquad.team3.baseball.dto.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class MainController {

    private MainDAO mainDAO;

    public MainController(MainDAO mainDAO) {
        this.mainDAO = mainDAO;
    }

    @GetMapping("/teams")
    public ResponseEntity<ResponseData> getTeams() {
        HashMap<String, Object> teams = new HashMap<>();
        teams.put("teams", mainDAO.selectAllTeams());
        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, teams), HttpStatus.OK);
    }

    @GetMapping("/games")
    public ResponseEntity<ResponseData> getTeamGames() {
        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, mainDAO.selectAllTeamGames()), HttpStatus.OK);
    }

    @PatchMapping("/games/{gameId}/teams/{teamId}")
    public ResponseEntity<ResponseData> selectGame(@PathVariable int gameId,
                                                   @PathVariable int teamId) {
        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, null), HttpStatus.OK);
    }
}
