package com.codesquad.team3.baseball.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BoardDTO {

    private boolean isHome;

    private ScoreDTO home;

    private ScoreDTO away;

    public BoardDTO(boolean isHome, ScoreDTO home, ScoreDTO away) {
        this.isHome = isHome;
        this.home = home;
        this.away = away;
    }
}
