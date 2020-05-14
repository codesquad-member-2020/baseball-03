package com.codesquad.team3.baseball.domain;

import com.codesquad.team3.baseball.dto.Result;

public class Player {

    private Integer id;
    private String name;
    private boolean isPitcher;
    private float average;
    private int battingOrder;
    private Integer team;
    private Integer atBats;
    private Integer hitCount;
    private Integer outCount;
    private Integer pitches;

    private static final int TOTAL = 1000;
    private static final int CHANCE_OF_OUT = 50;
    private int chanceOfHit;
    private int chanceOfStrikeOrBall;

    public Player(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.isPitcher = builder.isPitcher;
        this.average = builder.average;
        this.battingOrder = builder.battingOrder;
        this.team = builder.team;
        this.atBats = builder.atBats;
        this.hitCount = builder.hitCount;
        this.outCount = builder.outCount;
        this.pitches = builder.pitches;
        if (!this.isPitcher) {
            this.chanceOfHit = (int)(average * TOTAL);
            this.chanceOfStrikeOrBall = ((TOTAL - chanceOfHit) / 2 - CHANCE_OF_OUT);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getAverage() {
        return average;
    }

    public int getBattingOrder() {
        return battingOrder;
    }

    public Integer getAtBats() {
        return atBats;
    }

    public Integer getHitCount() {
        return hitCount;
    }

    public Integer getOutCount() {
        return outCount;
    }

    public Integer getPitches() {
        return pitches;
    }

    public Result hit() {
        int result = (int)(Math.random() * TOTAL);

        if (result < chanceOfHit){
            return Result.HIT;
        }
        if (result < chanceOfHit + chanceOfStrikeOrBall){
            return Result.STRIKE;
        }
        if (result < (chanceOfHit + chanceOfStrikeOrBall * 2)) {
            return Result.BALL;
        }
        return Result.OUT;
    }

    public void addPitches() {
        pitches++;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private boolean isPitcher;
        private float average;
        private int battingOrder;
        private Integer team;
        private int atBats;
        private int hitCount;
        private int outCount;
        private int pitches;

        public Builder(Integer id) {
            this.id = id;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder isPitcher(boolean isPitcher) {
            this.isPitcher = isPitcher;
            return this;
        }

        public Builder average(float average) {
            this.average = average;
            return this;
        }

        public Builder battingOrder(int battingOrder) {
            this.battingOrder = battingOrder;
            return this;
        }

        public Builder team(Integer team) {
            this.team = team;
            return this;
        }

        public Builder atBats(Integer atBats) {
            this.atBats = atBats;
            return this;
        }

        public Builder hitCount(Integer hitCount) {
            this.hitCount = hitCount;
            return this;
        }

        public Builder outCount(Integer outCount) {
            this.outCount = outCount;
            return this;
        }

        public Builder pitches(Integer pitches) {
            this.pitches = pitches;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }
}
