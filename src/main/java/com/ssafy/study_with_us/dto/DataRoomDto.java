package com.ssafy.study_with_us.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class DataRoomDto extends FileDto {
    private Long id;
    private String subject;
    private String content;
    private MemberDto member;
    private StudyDto study;

    public DataRoomDto() {
    }
    @Builder
    public DataRoomDto(String sysName, String orgName, String path, LocalDateTime regTime, Long id, String subject, String content, MemberDto member, StudyDto study) {
        super(sysName, orgName, path, regTime);
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.member = member;
        this.study = study;
    }

    @Override
    public String toString() {
        return "DataRoomDto{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", member=" + member +
                ", study=" + study +
                '}';
    }
}
