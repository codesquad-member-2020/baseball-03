package com.codesquad.team3.baseball.domain;

import org.springframework.data.annotation.Id;

public class User {
    @Id
    private Integer id;

    private String email;

    private String password;

    private String nickname;

    private int win;

    private int lose;

    private int draw;

    private Integer team;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setTeam(Integer team) {
        this.team = team;
    }

    public Integer getId() {
        return id;
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }

    public boolean teamIsNull() {
        return team == null;
    }

}
