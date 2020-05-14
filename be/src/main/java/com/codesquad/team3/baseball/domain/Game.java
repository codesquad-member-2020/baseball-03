package com.codesquad.team3.baseball.domain;

import java.util.List;

public class Game {

    private Integer id;
    private int homeBattingOrder;
    private int awayBattingOrder;
    private boolean isOver;
    private List<HalfInning> halfInnings;

    public Game(Integer id, int homeBattingOrder, int awayBattingOrder, boolean isOver) {
        this.id = id;
        this.homeBattingOrder = homeBattingOrder;
        this.awayBattingOrder = awayBattingOrder;
        this.isOver = isOver;
    }

    public Integer getId() {
        return id;
    }

    public List<HalfInning> getHalfInnings() {
        return halfInnings;
    }

    public void nextHomeBattingOrder() {
        homeBattingOrder = ++homeBattingOrder == 10 ? 1 : homeBattingOrder;
    }

    public void nextAwayBattingOrder() {
        awayBattingOrder = ++awayBattingOrder == 10 ? 1 : awayBattingOrder;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public int getHomeBattingOrder() {
        return homeBattingOrder;
    }

    public int getAwayBattingOrder() {
        return awayBattingOrder;
    }

    public void setHalfInnings(List<HalfInning> halfInnings) {
        this.halfInnings = halfInnings;
    }

    public boolean isTop() {
        return halfInnings.get(halfInnings.size() - 1).isTop();
    }

    public HalfInning getLastHalfInning() {
        return halfInnings.get(halfInnings.size() - 1);
    }

    public int getRounds() {
        return halfInnings.size();
    }

    public void addHalfInning(HalfInning halfInning) {
        halfInnings.add(halfInning);
    }
}
