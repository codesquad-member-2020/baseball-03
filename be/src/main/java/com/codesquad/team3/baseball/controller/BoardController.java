package com.codesquad.team3.baseball.controller;

import com.codesquad.team3.baseball.dto.ResponseData;
import com.codesquad.team3.baseball.dto.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/games/{gameId}/teams/{teamId}")
public class BoardController {

    @GetMapping("/scores")
    public ResponseEntity<ResponseData> getScores(@PathVariable Integer gameId,
                                                  @PathVariable Integer teamId) {
        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, null), HttpStatus.OK);
    }

    @GetMapping("/records")
    public ResponseEntity<ResponseData> getRecords(@PathVariable Integer gameId,
                                                   @PathVariable Integer teamId) {
        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, null), HttpStatus.OK);
    }
}
