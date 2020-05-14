package com.codesquad.team3.baseball.domain;

import org.apache.catalina.User;

public class TeamGame {

    private Integer team;
    private Game game;
    private User user;
    private boolean isHome;

    public TeamGame(Integer team, Game game, User user, boolean isHome) {
        this.team = team;
        this.game = game;
        this.user = user;
        this.isHome = isHome;
    }

    public Integer getTeam() {
        return team;
    }

    public Game getGame() {
        return game;
    }

    public boolean isHome() {
        return isHome;
    }

    public boolean isTop() {
        return game.isTop();
    }

    public HalfInning getLastHalfInning() {
        return game.getLastHalfInning();
    }
}
