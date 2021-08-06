package com.ssafy.study_with_us.dto;

import com.ssafy.study_with_us.domain.entity.Member;
import com.ssafy.study_with_us.domain.entity.Study;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StudyMemberDto {
    private Long id;
    private StudyDto study;
    private MemberDto member;
    private LocalDateTime recentlyConnectionTime;

    public StudyMemberDto() {
    }

    @Builder
    public StudyMemberDto(Long id, StudyDto study, MemberDto member, LocalDateTime recentlyConnectionTime) {
        this.id = id;
        this.study = study;
        this.member = member;
        this.recentlyConnectionTime = recentlyConnectionTime;
    }

    @Override
    public String toString() {
        return "StudyMemberDto{" +
                "id=" + id +
                ", study=" + study +
                ", member=" + member +
                ", recentlyConnectionTime=" + recentlyConnectionTime +
                '}';
    }
}
