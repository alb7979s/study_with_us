package com.ssafy.study_with_us.dto;

import com.ssafy.study_with_us.domain.entity.Profile;
import lombok.Builder;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class StudyDto {
    private Long id;
    private String studyName;
    private String studyIntro;
    private Long studyLeader;
    private String security;
    private Set<String> themes = new HashSet<>();
    private Profile profile;

    public StudyDto() {
    }

    @Builder
    public StudyDto(Long id, String studyName, String studyIntro, Long studyLeader, String security, Set<String> themes, Profile profile) {
        this.id = id;
        this.studyName = studyName;
        this.studyIntro = studyIntro;
        this.studyLeader = studyLeader;
        this.security = security;
        this.themes = themes;
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "StudyDto{" +
                "id=" + id +
                ", studyName='" + studyName + '\'' +
                ", studyIntro='" + studyIntro + '\'' +
                ", studyLeader=" + studyLeader +
                ", security='" + security + '\'' +
                ", themes=" + themes +
                ", profile=" + profile +
                '}';
    }
}
