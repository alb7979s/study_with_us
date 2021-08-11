package com.ssafy.study_with_us.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
public class ScheduleDto {
    private Long scheduleId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate scheduleDate;
    private String info;
    private Long studyId;

    public ScheduleDto() {
    }

    @Builder
    public ScheduleDto(Long scheduleId, LocalDate scheduleDate, String info, Long studyId) {
        this.scheduleId = scheduleId;
        this.scheduleDate = scheduleDate;
        this.info = info;
        this.studyId = studyId;
    }

    @Override
    public String toString() {
        return "ScheduleDto{" +
                "scheduleId=" + scheduleId +
                ", scheduleDate=" + scheduleDate +
                ", info='" + info + '\'' +
                ", studyId=" + studyId +
                '}';
    }
}
