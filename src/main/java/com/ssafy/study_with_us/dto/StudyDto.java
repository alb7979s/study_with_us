package com.ssafy.study_with_us.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StudyDto {
    private Long id;
    private String studyName;
    private String studyIntro;
    private String studyLeader;
    private String security;

    public StudyDto() {
    }

    @Builder
    public StudyDto(Long id, String studyName, String studyIntro, String studyLeader, String security) {
        this.id = id;
        this.studyName = studyName;
        this.studyIntro = studyIntro;
        this.studyLeader = studyLeader;
        this.security = security;
    }

    @Override
    public String toString() {
        return "StudyDto{" +
                "id=" + id +
                ", studyName='" + studyName + '\'' +
                ", studyIntro='" + studyIntro + '\'' +
                ", studyLeader='" + studyLeader + '\'' +
                ", security='" + security + '\'' +
                '}';
    }
}
