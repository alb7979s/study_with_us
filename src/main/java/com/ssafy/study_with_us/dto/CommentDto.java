package com.ssafy.study_with_us.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CommentDto {
    private Long commentId;
    private String content;
    private LocalDate regTime;
    private Long studyId;
    private Long memberId;

    public CommentDto() {
    }

    @Builder
    public CommentDto(Long commentId, String content, LocalDate regTime, Long studyId, Long memberId) {
        this.commentId = commentId;
        this.content = content;
        this.regTime = regTime;
        this.studyId = studyId;
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "commentId=" + commentId +
                ", content='" + content + '\'' +
                ", regTime=" + regTime +
                ", studyId=" + studyId +
                ", memberId=" + memberId +
                '}';
    }
}
