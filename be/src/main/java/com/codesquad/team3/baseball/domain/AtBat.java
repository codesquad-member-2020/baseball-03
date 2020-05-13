package com.codesquad.team3.baseball.domain;

public class AtBat {

    private Integer halfInning;
    private Integer hitter;
    private int strike;
    private int ball;

    public AtBat(Integer halfInning, Integer hitter, int strike, int ball) {
        this.halfInning = halfInning;
        this.hitter = hitter;
        this.strike = strike;
        this.ball = ball;
    }

    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }

    public int addStrike() {
        return ++strike;
    }

    public int addBall() {
        return ++ball;
    }

    public boolean is3Strikes() {
        return strike == 3;
    }

    public boolean is4Balls() {
        return ball == 4;
    }
}
