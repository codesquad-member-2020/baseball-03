package com.codesquad.team3.baseball.controller;

import com.codesquad.team3.baseball.dto.ExceptionDTO;
import com.codesquad.team3.baseball.dto.ResponseData;
import com.codesquad.team3.baseball.dto.Status;
import com.codesquad.team3.baseball.exception.CanNotSelectException;
import com.codesquad.team3.baseball.exception.NonLoginException;
import com.codesquad.team3.baseball.exception.NotFoundException;
import com.codesquad.team3.baseball.service.MatchingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MatchingController {

    private MatchingService matchingService;

    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    @GetMapping("/teams")
    public ResponseEntity<ResponseData> getTeams() {
        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, matchingService.getAllTeams()), HttpStatus.OK);
    }

    @GetMapping("/games")
    public ResponseEntity<ResponseData> getGames() {

        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, matchingService.getAllGames()), HttpStatus.OK);
    }

    @PatchMapping("/games/{gameId}/teams/{teamId}")
    public ResponseEntity<ResponseData> selectGame(@PathVariable int gameId,
                                                   @PathVariable int teamId,
                                                   HttpSession session) {
        if(!checkLogin(session)) {
            throw new NonLoginException();
        }

        Map<String, Boolean> content = matchingService.getSelectionResult(gameId, teamId);
        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, content), HttpStatus.OK);
    }

    @GetMapping("/games/{gameId}/teams/{teamId}/matching")
    public ResponseEntity<ResponseData> selectGame() {
        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, null), HttpStatus.OK);
    }

    @ExceptionHandler(NonLoginException.class)
    public ResponseEntity<ExceptionDTO> cannotLogin() {
        Map<String, String> content = new HashMap<>();
        content.put("message", "로그인 해주세요.");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionDTO("ERROR", content));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDTO> notFound() {
        Map<String, String> content = new HashMap<>();
        content.put("message", "게임 혹은 팀을 찾을 수 없습니다.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDTO("ERROR", content));
    }

    @ExceptionHandler(CanNotSelectException.class)
    public ResponseEntity<ExceptionDTO> catNotSelect() {
        Map<String, String> content = new HashMap<>();
        content.put("message", "이미 선택된 팀입니다.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDTO("ERROR", content));
    }

    private boolean checkLogin(HttpSession session) {
        return session.getAttribute("user") != null;
    }
}
