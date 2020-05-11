package com.codesquad.team3.baseball.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class HalfInningDTO {

    private int round;

    private boolean isTop;

    private boolean isAttack;

    public HalfInningDTO(int round, boolean isTop, boolean isAttack) {
        this.round = round;
        this.isTop = isTop;
        this.isAttack = isAttack;
    }
}
