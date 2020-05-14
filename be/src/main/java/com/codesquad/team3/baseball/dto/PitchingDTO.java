package com.codesquad.team3.baseball.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PitchingDTO {

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean isOver;

    private boolean isHome;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, String> team;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Integer> gameScore;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private HalfInningDTO halfInning;

    private PitcherDTO pitcher;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private HitterDTO hitter;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private LogDTO log;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<Base, Boolean> base;

    public PitchingDTO(Builder builder) {
        this.isOver = builder.isOver;
        this.isHome = builder.isHome;
        this.team = builder.team;
        this.gameScore = builder.gameScore;
        this.halfInning = builder.halfInning;
        this.pitcher = builder.pitcher;
        this.hitter = builder.hitter;
        this.log = builder.log;
    }

    public static class Builder {

        private boolean isOver;

        private boolean isHome;

        private Map<String, String> team;

        private Map<String, Integer> gameScore;

        private HalfInningDTO halfInning;

        private PitcherDTO pitcher;

        private HitterDTO hitter;

        private LogDTO log;

        private Map<Base, Boolean> base;

        public Builder isOver(boolean isOver) {
            this.isOver = isOver;
            return this;
        }

        public Builder isHome(boolean isHome) {
            this.isHome = isHome;
            return this;
        }

        public Builder team(Map<String, String> team) {
            this.team = team;
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

        public Builder pitcher(PitcherDTO pitcher) {
            this.pitcher = pitcher;
            return this;
        }

        public Builder hitter(HitterDTO hitter) {
            this.hitter = hitter;
            return this;
        }

        public Builder log(LogDTO log) {
            this.log = log;
            return this;
        }

        public Builder base(Map<Base, Boolean> base) {
            this.base = base;
            return this;
        }

        public PitchingDTO build() {
            return new PitchingDTO(this);
        }
    }
}
