package com.codesquad.team3.baseball.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PlayerDTO {

    private String name;

    private int pitchCount;

    private int order;

    private int atBats;

    private int hits;

    private int outs;

    private float avg;

    public PlayerDTO(Builder builder) {
        this.name = builder.name;
        this.pitchCount = builder.pitchCount;
        this.order = builder.order;
        this.atBats = builder.atBats;
        this.hits = builder.hits;
        this.outs = builder.outs;
        this.avg = builder.avg;
    }

    public int getAtBats() {
        return atBats;
    }

    public int getHits() {
        return hits;
    }

    public int getOuts() {
        return outs;
    }

    public static class Builder {

        private String name;

        private int pitchCount;

        private int order;

        private int atBats;

        private int hits;

        private int outs;

        private float avg;

        public Builder(String name) {
            this.name = name;
        }

        public Builder pitchCount(int pitchCount) {
            this.pitchCount = pitchCount;
            return this;
        }

        public Builder order(int order) {
            this.order = order;
            return this;
        }

        public Builder atBats(int atBats) {
            this.atBats = atBats;
            return this;
        }

        public Builder hits(int hits) {
            this.hits = hits;
            return this;
        }

        public Builder outs(int outs) {
            this.outs = outs;
            return this;
        }

        public Builder avg(float avg) {
            this.avg = avg;
            return this;
        }

        public PlayerDTO build() {
            return new PlayerDTO(this);
        }
    }
}
