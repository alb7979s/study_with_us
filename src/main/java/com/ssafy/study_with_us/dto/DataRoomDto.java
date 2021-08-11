package com.ssafy.study_with_us.dto;

import com.ssafy.study_with_us.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class DataRoomDto{
    private Long id;
    private String subject;
    private String content;
    private MemberDto member;
    private Long studyId;
    private List<FileDto> files;
    public DataRoomDto() {
    }

    @Builder
    public DataRoomDto(Long id, String subject, String content, MemberDto member, Long studyId, List<FileDto> files) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.member = member;
        this.studyId = studyId;
        this.files = files;
    }

    @Override
    public String toString() {
        return "DataRoomDto{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", memberDto=" + member +
                ", studyId=" + studyId +
                ", files=" + files +
                '}';
    }
}
