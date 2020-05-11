package com.codesquad.team3.baseball.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GameStatusDTO {

    private boolean isHome;

    private boolean isReady;

    public GameStatusDTO(boolean isHome, boolean isReady) {
        this.isHome = isHome;
        this.isReady = isReady;
    }
}
