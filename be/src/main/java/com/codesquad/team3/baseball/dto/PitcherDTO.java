package com.codesquad.team3.baseball.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PitcherDTO {
    private String name;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int pitchCount;

    public PitcherDTO(String name) {
        this.name = name;
    }

    public PitcherDTO(String name, int pitchCount) {
        this.name = name;
        this.pitchCount = pitchCount;
    }
}
