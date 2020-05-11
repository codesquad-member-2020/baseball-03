package com.codesquad.team3.baseball.dto;

public enum Result {
    STRIKE("스트라이크"),
    HIT("안타"),
    BALL("볼"),
    OUT("아웃");

    private String value;

    private Result(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
