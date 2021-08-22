package com.ssafy.study_with_us.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StudyMemberRefDto {
    private Long id;
    private String nickname;
    private Boolean connected;
    private Long studyId;
    private Long memberId;
    private LocalDateTime recentlyConnectionTime;

    public StudyMemberRefDto() {
    }

    @Builder
    public StudyMemberRefDto(Long id, String nickname, Boolean connected, Long studyId, Long memberId, LocalDateTime recentlyConnectionTime) {
        this.id = id;
        this.nickname = nickname;
        this.connected = connected;
        this.studyId = studyId;
        this.memberId = memberId;
        this.recentlyConnectionTime = recentlyConnectionTime;
    }

    @Override
    public String toString() {
        return "StudyMemberRefDto{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", connected=" + connected +
                ", studyId=" + studyId +
                ", memberId=" + memberId +
                ", recentlyConnectionTime=" + recentlyConnectionTime +
                '}';
    }
}
