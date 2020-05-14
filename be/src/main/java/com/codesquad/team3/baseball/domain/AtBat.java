package com.codesquad.team3.baseball.domain;

public class AtBat {

    private Integer id;
    private Integer halfInning;
    private Integer hitter;
    private int strikeCount;
    private int ballCount;

    public AtBat(Integer id, Integer halfInning, Integer hitter, int strikeCount, int ballCount) {
        this.id = id;
        this.halfInning = halfInning;
        this.hitter = hitter;
        this.strikeCount = strikeCount;
        this.ballCount = ballCount;
    }
    
    public Integer getId() {
        return id;
    }

    public int getStrikeCount() {
        return strikeCount;
    }

    public int getBallCount() {
        return ballCount;
    }

    public int addStrike() {
        return ++strikeCount;
    }

    public int addBall() {
        return ++ballCount;
    }

    public boolean is3Strikes() {
        return strikeCount == 3;
    }

    public boolean is4Balls() {
        return ballCount == 4;
    }
}
