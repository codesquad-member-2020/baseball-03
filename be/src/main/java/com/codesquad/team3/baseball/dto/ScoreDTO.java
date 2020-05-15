package com.codesquad.team3.baseball.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ScoreDTO {

    private String name;

    private boolean isAttack;

    private List<Integer> scores;

    private int total;

    public ScoreDTO() {}

    public ScoreDTO(String name, boolean isAttack, List<Integer> scores) {
        this.name = name;
        this.isAttack = isAttack;
        this.scores = scores;
        this.total = sumScores();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttack(boolean attack) {
        isAttack = attack;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }

    private int sumScores() {
        System.out.println("Sfasdf:" + this.scores);
        return this.scores.stream().reduce(Integer::sum).get();
    }

}
