package com.codesquad.team3.baseball.domain;

import com.codesquad.team3.baseball.dto.Result;

public class HalfInning {

    private Integer id;
    private int inning;
    private boolean isTop;
    private int score;
    private int outCount;
    private boolean firstBase;
    private boolean secondBase;
    private boolean thirdBase;
    private boolean homeBase;

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
        this.outCount = builder.outCount;
        this.firstBase = builder.firstBase;
        this.secondBase = builder.secondBase;
        this.thirdBase = builder.thirdBase;
        this.homeBase = builder.homeBase;
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

    public int getOutCount() {
        return outCount;
    }

    public boolean isFirstBase() {
        return firstBase;
    }

    public boolean isSecondBase() {
        return secondBase;
    }

    public boolean isThirdBase() {
        return thirdBase;
    }

    public boolean isHomeBase() {
        return homeBase;
    }

    public int addScore(){
        return this.score++;
    }

    public int addOut(){
        return this.outCount++;
    }

    public int initOut() {
        return this.outCount = 0;
    }

    public boolean turnFirst(boolean status) {
        return this.firstBase = status;
    }

    public boolean turnSecond(boolean status) {
        return this.secondBase = status;
    }

    public boolean turnThird(boolean status) {
        return this.thirdBase = status;
    }

    public boolean update(Result result, AtBat atBat) {
        switch (result) {
            case HIT:
                updateBases();
                return true;
            case STRIKE:
                atBat.addStrike();
                if (atBat.is3Strikes()) {
                    outCount++;
                    return true;
                }
                return false;
            case OUT:
                outCount++;
                if (outCount == 3) {
                    return true;
                }
                return true;
            case BALL:
                atBat.addBall();
                if (atBat.is4Balls()) {
                    updateBases();
                    return true;
                }
                return false;
        }
        return false;
    }

    public boolean isOver() {
        return outCount == 3;
    }

    private void updateBases() {
        if (thirdBase) {
            addScore();
            this.homeBase = true;
        }
        if (secondBase) {
            this.thirdBase = true;
        }
        if (firstBase) {
            this.secondBase = true;
        }
        this.firstBase = true;
    }

    @Override
    public String toString() {
        return "HalfInning{" +
                "id=" + id +
                ", inning=" + inning +
                ", isTop=" + isTop +
                ", score=" + score +
                ", outCount=" + outCount +
                ", firstBase=" + firstBase +
                ", secondBase=" + secondBase +
                ", thirdBase=" + thirdBase +
                ", homeBase=" + homeBase +
                '}';
    }

    public static class Builder {

        private Integer id;
        private int inning;
        private boolean isTop;
        private int score;
        private int outCount;
        private boolean firstBase;
        private boolean secondBase;
        private boolean thirdBase;
        private boolean homeBase;

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

        public Builder outCount(int outCount) {
            this.outCount = outCount;
            return this;
        }

        public Builder firstBase(boolean firstBase) {
            this.firstBase = firstBase;
            return this;
        }

        public Builder secondBase(boolean secondBase) {
            this.secondBase = secondBase;
            return this;
        }

        public Builder thirdBase(boolean thirdBase) {
            this.thirdBase = thirdBase;
            return this;
        }

        public Builder homeBase(boolean homeBase) {
            this.homeBase = homeBase;
            return this;
        }

        public HalfInning build() {
            return new HalfInning(this);
        }
    }
}
