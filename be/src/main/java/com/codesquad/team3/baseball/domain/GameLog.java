package com.codesquad.team3.baseball.domain;

import com.codesquad.team3.baseball.dto.Result;

import java.time.LocalDateTime;

public class GameLog {

    private Result result;
    private LocalDateTime createTime;
    private Integer pitcher;
    private Integer hitter;
    private Integer atBat;

    public GameLog(Result result, LocalDateTime createTime, Integer atBat) {
        this.result = result;
        this.createTime = createTime;
        this.atBat = atBat;
    }

    public GameLog(Result result, LocalDateTime createTime, Integer pitcher, Integer hitter, Integer atBat) {
        this.result = result;
        this.createTime = createTime;
        this.pitcher = pitcher;
        this.hitter = hitter;
        this.atBat = atBat;
    }

    public Result getResult() {
        return result;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Integer getPitcher() {
        return pitcher;
    }

    public Integer getHitter() {
        return hitter;
    }

    public Integer getAtBat() {
        return atBat;
    }
}
