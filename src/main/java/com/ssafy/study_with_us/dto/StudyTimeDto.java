package com.ssafy.study_with_us.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class StudyTimeDto {

    private Long studyTimeId;
    private LocalDate studyDate;
    private Long studyTime;
    private Long memberId;

    public StudyTimeDto() {
    }

    @Builder
    public StudyTimeDto(Long studyTimeId, LocalDate studyDate, Long studyTime, Long memberId) {
        this.studyTimeId = studyTimeId;
        this.studyDate = studyDate;
        this.studyTime = studyTime;
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "StudyTimeDto{" +
                "studyTimeId=" + studyTimeId +
                ", studyDate=" + studyDate +
                ", studyTime=" + studyTime +
                ", memberId=" + memberId +
                '}';
    }
}
