package com.codesquad.team3.baseball.domain;

public class Game {

    private Integer id;
    private int homeBattingOrder;
    private int awayBattingOrder;
    private boolean isOver;

    public Game(Integer id, int homeBattingOrder, int awayBattingOrder, boolean isOver) {
        this.id = id;
        this.homeBattingOrder = homeBattingOrder;
        this.awayBattingOrder = awayBattingOrder;
        this.isOver = isOver;
    }
}
