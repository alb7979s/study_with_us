package com.ssafy.study_with_us.dto;

import lombok.Builder;

public class BlacklistDto {
    private Long id;
    private StudyDto study;
    private MemberDto member;

    public BlacklistDto() {
    }

    @Builder
    public BlacklistDto(Long id, StudyDto study, MemberDto member) {
        this.id = id;
        this.study = study;
        this.member = member;
    }

    @Override
    public String toString() {
        return "BlacklistDto{" +
                "id=" + id +
                ", study=" + study +
                ", member=" + member +
                '}';
    }
}
