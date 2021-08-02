package com.ssafy.study_with_us.dto;

import com.ssafy.study_with_us.domain.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CommentDto {
    private Long id;
    private String content;
    private LocalDate regTime;
    private Long studyId;
    private Long memberId;

    public CommentDto() {
    }

    @Builder
    public CommentDto(Long id, String content, LocalDate regTime, Long studyId, Long memberId) {
        this.id = id;
        this.content = content;
        this.regTime = regTime;
        this.studyId = studyId;
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", regTime=" + regTime +
                ", studyId=" + studyId +
                ", memberId=" + memberId +
                '}';
    }
}
