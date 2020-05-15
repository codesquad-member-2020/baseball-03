package com.codesquad.team3.baseball.domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtBat atBat = (AtBat) o;
        return Objects.equals(id, atBat.id) &&
                Objects.equals(halfInning, atBat.halfInning) &&
                Objects.equals(hitter, atBat.hitter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, halfInning, hitter);
    }
}
