package com.codesquad.team3.baseball.exception;

public class InAppropriateRequest extends RuntimeException {
    public InAppropriateRequest() {
        super("부적절한 요청입니다.");
    }
}
