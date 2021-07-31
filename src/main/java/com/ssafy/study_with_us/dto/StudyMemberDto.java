package com.ssafy.study_with_us.dto;

import com.ssafy.study_with_us.domain.entity.Member;
import com.ssafy.study_with_us.domain.entity.Study;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StudyMemberDto {
    private Long id;
    private Long studyId;
    private Long memberId;

    public StudyMemberDto() {
    }

    @Builder
    public StudyMemberDto(Long id, Long studyId, Long memberId) {
        this.id = id;
        this.studyId = studyId;
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "StudyMemberDto{" +
                "id=" + id +
                ", studyId=" + studyId +
                ", memberId=" + memberId +
                '}';
    }
}
