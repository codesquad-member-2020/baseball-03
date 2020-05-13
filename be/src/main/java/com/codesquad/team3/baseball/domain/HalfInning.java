package com.codesquad.team3.baseball.domain;

import com.codesquad.team3.baseball.dto.Result;

public class HalfInning {

    private Integer id;
    private int inning;
    private boolean isTop;
    private int score;
    private int strike;
    private int ball;
    private int out;
    private boolean first;
    private boolean second;
    private boolean third;
    private boolean home;

    public HalfInning(Integer id, int inning, boolean isTop) {
        this.id = id;
        this.inning = inning;
        this.isTop = isTop;
    }

    public HalfInning(Builder builder) {
        this.id = builder.id;
        this.inning = builder.inning;
        this.isTop = builder.isTop;
        this.score = builder.score;
        this.strike = builder.strike;
        this.ball = builder.ball;
        this.out = builder.out;
        this.first = builder.first;
        this.second = builder.second;
        this.third = builder.third;
        this.home = builder.home;
    }

    public Integer getId() {
        return id;
    }

    public int getInning() {
        return inning;
    }

    public boolean isTop() {
        return isTop;
    }

    public int getScore() {
        return score;
    }

    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }

    public int getOut() {
        return out;
    }

    public boolean isFirst() {
        return first;
    }

    public boolean isSecond() {
        return second;
    }

    public boolean isThird() {
        return third;
    }

    public boolean isHome() {
        return home;
    }

    public int addScore(){
        return this.score++;
    }

    public int addStrike(){
        return this.strike++;
    }

    public int initStrike() {
        return this.strike = 0;
    }

    public int addBall(){
        return this.ball++;
    }

    public int initBall() {
        return this.ball = 0;
    }

    public int addOut(){
        return this.out++;
    }

    public int initOut() {
        return this.out = 0;
    }

    public void initAtBat() {
        this.ball = 0;
        this.strike = 0;
    }

    public boolean turnFirst(boolean status) {
        return this.first = status;
    }

    public boolean turnSecond(boolean status) {
        return this.second = status;
    }

    public boolean turnThird(boolean status) {
        return this.third = status;
    }

    public boolean update(Result result) {
        switch (result) {
            case HIT:
                updateBases();
                return true;
            case STRIKE:
                strike++;
                if (strike == 3) {
                    out++;
                    return true;
                }
                return false;
            case OUT:
                out++;
                if (out == 3) {
                    return true;
                }
                return true;
            case BALL:
                ball++;
                if (ball == 4) {
                    updateBases();
                    return true;
                }
                return false;
        }
        return false;
    }

    public boolean is3Strikes() {
        return strike == 3;
    }

    public boolean isOver() {
        return out == 3;
    }

    private void updateBases() {
        if (third) {
            addScore();
            this.third = false;
            this.home = true;
        }
        if (second) {
            this.third = true;
            this.second = false;
        }
        if (first) {
            this.second = true;
        }
        this.first = false;
    }

    @Override
    public String toString() {
        return "HalfInning{" +
                "id=" + id +
                ", inning=" + inning +
                ", isTop=" + isTop +
                ", score=" + score +
                ", strike=" + strike +
                ", ball=" + ball +
                ", out=" + out +
                ", first=" + first +
                ", second=" + second +
                ", third=" + third +
                '}';
    }

    public static class Builder {

        private Integer id;
        private int inning;
        private boolean isTop;
        private int score;
        private int strike;
        private int ball;
        private int out;
        private boolean first;
        private boolean second;
        private boolean third;
        private boolean home;

        public Builder(Integer id) {
            this.id = id;
        }

        public Builder inning(int inning) {
            this.inning = inning;
            return this;
        }

        public Builder isTop(boolean isTop) {
            this.isTop = isTop;
            return this;
        }

        public Builder score(int score) {
            this.score = score;
            return this;
        }

        public Builder strike(int strike) {
            this.strike = strike;
            return this;
        }

        public Builder ball(int ball) {
            this.ball = ball;
            return this;
        }

        public Builder out(int out) {
            this.out = out;
            return this;
        }

        public Builder first(boolean first) {
            this.first = first;
            return this;
        }

        public Builder second(boolean second) {
            this.second = second;
            return this;
        }

        public Builder third(boolean third) {
            this.third = third;
            return this;
        }

        public Builder home(boolean home) {
            this.home = home;
            return this;
        }

        public HalfInning build() {
            return new HalfInning(this);
        }
    }
}
