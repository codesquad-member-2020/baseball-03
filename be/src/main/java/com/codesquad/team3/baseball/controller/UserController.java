package com.codesquad.team3.baseball.controller;

import com.codesquad.team3.baseball.dao.UserDAO;
import com.codesquad.team3.baseball.domain.User;
import com.codesquad.team3.baseball.dto.ExceptionDTO;
import com.codesquad.team3.baseball.dto.ResponseData;
import com.codesquad.team3.baseball.dto.Status;
import com.codesquad.team3.baseball.exception.CanNotLoginException;
import com.codesquad.team3.baseball.exception.NotFoundException;
import com.codesquad.team3.baseball.exception.NotMatchPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseData> login(@RequestParam String email,
                                              @RequestParam String password,
                                              HttpSession session) {

        User user = userDAO.getUser(email);

        if(checkLogined(session)){
            throw new CanNotLoginException();
        }

        if(!user.matchPassword(password)) {
            throw new NotMatchPasswordException();
        }

        session.setAttribute("user", user);

        Map<String, Boolean> content = new HashMap<>();
        content.put("isLogined", true);

        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, content), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<ResponseData> logout(HttpSession session){
        session.removeAttribute("user");

        Map<String, Boolean> content = new HashMap<>();
        content.put("logout", true);

        return new ResponseEntity<>(new ResponseData(Status.SUCCESS, content), HttpStatus.OK);

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDTO> notFoundUser() {
        Map<String, String> content = new HashMap<>();
        content.put("message", "존재하지 않는 회원입니다.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDTO("ERROR", content));
    }

    @ExceptionHandler(NotMatchPasswordException.class)
    public ResponseEntity<ExceptionDTO> notMatchUser() {
        Map<String, String> content = new HashMap<>();
        content.put("message", "비밀번호가 일치하지 않습니다.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionDTO("ERROR", content));
    }

    @ExceptionHandler(CanNotLoginException.class)
    public ResponseEntity<ExceptionDTO> cannotLogin() {
        Map<String, String> content = new HashMap<>();
        content.put("message", "이미 로그인 상태입니다.");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionDTO("ERROR", content));
    }

    private boolean checkLogined(HttpSession session) {
        return session.getAttribute("user") != null;
    }

}
