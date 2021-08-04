package com.ssafy.study_with_us.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TomatoDto {
    private Long id;
    private Integer count;
    private LocalDate date;
    private Long studyId;
    private Long memberId;

    public TomatoDto() {
    }

    @Builder
    public TomatoDto(Long id, Integer count, LocalDate date, Long studyId, Long memberId) {
        this.id = id;
        this.count = count;
        this.date = date;
        this.studyId = studyId;
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "TomatoDto{" +
                "id=" + id +
                ", count=" + count +
                ", date=" + date +
                ", studyId=" + studyId +
                ", memberId=" + memberId +
                '}';
    }
}
