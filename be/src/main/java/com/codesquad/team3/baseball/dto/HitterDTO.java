package com.codesquad.team3.baseball.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class HitterDTO {
    private String name;

    private int order;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int atBats;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int hits;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int outs;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private float avg;

    public HitterDTO(Builder builder) {
        this.name = builder.name;
        this.order = builder.order;
        this.atBats = builder.atBats;
        this.hits = builder.hits;
        this.outs = builder.outs;
        this.avg = builder.avg;
    }

    public static class Builder {
        private String name;
        private int order;
        private int atBats;
        private int hits;
        private int outs;
        private float avg;

        public Builder name(String name) {
            this.name = name;
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

        public HitterDTO build() {
            return new HitterDTO(this);
        }
    }
}
