package com.codesquad.team3.baseball.domain;

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
    private int batting_order;

    public HalfInning(Integer id, int inning, boolean isTop) {
        this.id = id;
        this.inning = inning;
        this.isTop = isTop;
    }

    public void setBatting_order(int batting_order) {
        this.batting_order = batting_order;
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

    public boolean turnFirst(boolean status) {
        return this.first = status;
    }

    public boolean turnSecond(boolean status) {
        return this.second = status;
    }

    public boolean turnThird(boolean status) {
        return this.third = status;
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
}
