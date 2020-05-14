package com.codesquad.team3.baseball.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Map;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PlayerRecordDTO {

    private boolean isHome;

    // Key - players, totals
    private Map<String, Object> home;

    private Map<String, Object> away;

    public PlayerRecordDTO(boolean isHome, Map<String, Object> home, Map<String, Object> away) {
        this.isHome = isHome;
        this.home = home;
        this.away = away;
    }
}
