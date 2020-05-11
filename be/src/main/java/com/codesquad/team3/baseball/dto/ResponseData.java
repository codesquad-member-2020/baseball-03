package com.codesquad.team3.baseball.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ResponseData {

    private Status status;

    private Object content;

    public ResponseData(Status status, Object content) {
        this.status = status;
        this.content = content;
    }
}
