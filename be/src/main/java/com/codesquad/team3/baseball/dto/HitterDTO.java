package com.codesquad.team3.baseball.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class HitterDTO {
    private String name;
    private int order;
    private int atBats;
    private int hits;
    private int outs;
    private float avg;

    public HitterDTO(String name, int order, int atBats, int hits, int outs, float avg) {
        this.name = name;
        this.order = order;
        this.atBats = atBats;
        this.hits = hits;
        this.outs = outs;
        this.avg = avg;
    }
}
