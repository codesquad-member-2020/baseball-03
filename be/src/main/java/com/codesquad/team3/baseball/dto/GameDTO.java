package com.codesquad.team3.baseball.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GameDTO {

    @JsonProperty("matchId")
    private Integer id;

    private TeamDTO home;

    private TeamDTO away;

    public GameDTO(Integer id, TeamDTO home, TeamDTO away) {
        this.id = id;
        this.home = home;
        this.away = away;
    }
}
