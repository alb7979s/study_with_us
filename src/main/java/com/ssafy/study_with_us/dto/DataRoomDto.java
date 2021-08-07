package com.ssafy.study_with_us.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DataRoomDto{
    private Long id;
    private String subject;
    private String content;
    private Long memberId;
    private Long studyId;

    public DataRoomDto() {
    }

    @Builder
    public DataRoomDto(Long id, String subject, String content, Long memberId, Long studyId) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.memberId = memberId;
        this.studyId = studyId;
    }

    @Override
    public String toString() {
        return "DataRoomDto{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", memberId=" + memberId +
                ", studyId=" + studyId +
                '}';
    }
}
