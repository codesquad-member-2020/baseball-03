package com.codesquad.team3.baseball.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PitcherDTO {
    private String name;
    private int pitchCount;

    public PitcherDTO(String name) {
        this.name = name;
    }
}
