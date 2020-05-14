package com.codesquad.team3.baseball.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TeamDTO {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer id;

    private String name;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String url;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String thumbnail_url;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String color;

    public TeamDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.url = builder.url;
        this.thumbnail_url = builder.thumbnail_url;
        this.color = builder.color;
    }

    public static class Builder {

        private Integer id;

        private String name;

        private String url;

        private String thumbnail_url;

        private String color;

        public Builder(String name) {
            this.name = name;
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder thumbnailUrl(String thumbnail_url) {
            this.thumbnail_url = thumbnail_url;
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public TeamDTO build() {
            return new TeamDTO(this);
        }
    }
}
