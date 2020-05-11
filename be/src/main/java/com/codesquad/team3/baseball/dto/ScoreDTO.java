package com.codesquad.team3.baseball.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ScoreDTO {

    private String name;

    private boolean isAttack;

    private List<Integer> scores;

    private int total;

    public ScoreDTO(String name, boolean isAttack, List<Integer> scores, int total) {
        this.name = name;
        this.isAttack = isAttack;
        this.scores = scores;
        this.total = total;
    }
}
