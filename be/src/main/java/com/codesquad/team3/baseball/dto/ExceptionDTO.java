package com.codesquad.team3.baseball.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Map;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ExceptionDTO {
    private String status;
    private Map<String, String> content;

    public ExceptionDTO(String status, Map<String, String> content) {
        this.status = status;
        this.content = content;
    }
}
