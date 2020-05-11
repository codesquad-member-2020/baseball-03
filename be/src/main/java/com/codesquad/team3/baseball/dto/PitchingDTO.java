package com.codesquad.team3.baseball.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PitchingDTO {

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean isOver;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean isHome;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Integer> gameScore;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private HalfInningDTO halfInning;

    private PlayerDTO pitcher;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private PlayerDTO hitter;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private LogDTO log;

    public PitchingDTO(Builder builder) {
        this.isOver = builder.isOver;
        this.isHome = builder.isHome;
        this.gameScore = builder.gameScore;
        this.halfInning = builder.halfInning;
        this.pitcher = builder.pitcher;
        this.hitter = builder.hitter;
        this.log = builder.log;
    }

    public static class Builder {

        private boolean isOver;

        private boolean isHome;

        private Map<String, Integer> gameScore;

        private HalfInningDTO halfInning;

        private PlayerDTO pitcher;

        private PlayerDTO hitter;

        private LogDTO log;

        public Builder isOver(boolean isOver) {
            this.isOver = isOver;
            return this;
        }

        public Builder isHome(boolean isHome) {
            this.isHome = isHome;
            return this;
        }

        public Builder gameScore(Map<String, Integer> gameScore) {
            this.gameScore = gameScore;
            return this;
        }

        public Builder halfInning(HalfInningDTO halfInning) {
            this.halfInning = halfInning;
            return this;
        }

        public Builder pitcher(PlayerDTO pitcher) {
            this.pitcher = pitcher;
            return this;
        }

        public Builder hitter(PlayerDTO hitter) {
            this.hitter = hitter;
            return this;
        }

        public Builder log(LogDTO log) {
            this.log = log;
            return this;
        }

        public PitchingDTO build() {
            return new PitchingDTO(this);
        }
    }
}
