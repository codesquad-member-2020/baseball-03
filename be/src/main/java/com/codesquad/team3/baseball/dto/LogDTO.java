package com.codesquad.team3.baseball.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Map;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class LogDTO {

    private String result;

    private Map<Result, Integer> count;

    private boolean isOut;

    private boolean isHit;

    public LogDTO(Result result, Map<Result, Integer> count, boolean isOut, boolean isHit) {
        this.result = result.getValue();
        this.count = count;
        this.isOut = isOut;
        this.isHit = isHit;
    }
}
