package com.ssafy.study_with_us.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class IdReqDto {
    private Long memberId;
    private Long memberProfileId;
    private Long profileId;
    private Long scheduleId;
    private Long studyId;
    private Long studyMemberRefId;
    private Long studyProfileId;
    private Long studyThemeRefId;
    private Long themeId;

    public IdReqDto() {
    }

    @Builder
    public IdReqDto(Long memberId, Long memberProfileId, Long profileId, Long scheduleId, Long studyId, Long studyMemberRefId, Long studyProfileId, Long studyThemeRefId, Long themeId) {
        this.memberId = memberId;
        this.memberProfileId = memberProfileId;
        this.profileId = profileId;
        this.scheduleId = scheduleId;
        this.studyId = studyId;
        this.studyMemberRefId = studyMemberRefId;
        this.studyProfileId = studyProfileId;
        this.studyThemeRefId = studyThemeRefId;
        this.themeId = themeId;
    }

    @Override
    public String toString() {
        return "IdResponseDto{" +
                "memberId=" + memberId +
                ", memberProfileId=" + memberProfileId +
                ", profileId=" + profileId +
                ", scheduleId=" + scheduleId +
                ", studyId=" + studyId +
                ", studyMemberRefId=" + studyMemberRefId +
                ", studyProfileId=" + studyProfileId +
                ", studyThemeRefId=" + studyThemeRefId +
                ", themeId=" + themeId +
                '}';
    }
}
