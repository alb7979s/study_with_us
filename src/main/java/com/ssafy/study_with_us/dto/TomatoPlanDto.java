package com.ssafy.study_with_us.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TomatoPlanDto {
    private Long tomatoPlanId;
    private Integer goalTomato;
    private Integer goalTime;
    private LocalDate tomatoDate;
    private Long studyId;

    public TomatoPlanDto() {
    }
    @Builder
    public TomatoPlanDto(Long tomatoPlanId, Integer goalTomato, Integer goalTime, LocalDate tomatoDate, Long studyId) {
        this.tomatoPlanId = tomatoPlanId;
        this.goalTomato = goalTomato;
        this.goalTime = goalTime;
        this.tomatoDate = tomatoDate;
        this.studyId = studyId;
    }

    @Override
    public String toString() {
        return "TomatoPlanDto{" +
                "tomatoPlanId=" + tomatoPlanId +
                ", goalTomato=" + goalTomato +
                ", goalTime=" + goalTime +
                ", tomatoDate=" + tomatoDate +
                ", studyId=" + studyId +
                '}';
    }
}
