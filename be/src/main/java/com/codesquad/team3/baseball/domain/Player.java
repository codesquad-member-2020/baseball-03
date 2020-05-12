package com.codesquad.team3.baseball.domain;

public class Player {

    private Integer id;
    private String name;
    private boolean isPitcher;
    private float average;
    private int battingOrder;
    private Integer team;

    public Player(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.isPitcher = builder.isPitcher;
        this.average = builder.average;
        this.battingOrder = builder.battingOrder;
        this.team = builder.team;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private boolean isPitcher;
        private float average;
        private int battingOrder;
        private Integer team;

        public Builder(Integer id) {
            this.id = id;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder isPitcher(boolean isPitcher) {
            this.isPitcher = isPitcher;
            return this;
        }

        public Builder average(float average) {
            this.average = average;
            return this;
        }

        public Builder battingOrder(int battingOrder) {
            this.battingOrder = battingOrder;
            return this;
        }

        public Builder team(Integer team) {
            this.team = team;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }
}
