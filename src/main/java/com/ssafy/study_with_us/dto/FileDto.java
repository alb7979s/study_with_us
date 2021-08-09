package com.ssafy.study_with_us.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FileDto {
    private Long id;
    private String sysName;
    private String orgName;
    private String path;
    private Long fileSize;
    private String fileType;
    private LocalDateTime regTime;

    public FileDto() {
    }

    @Builder
    public FileDto(Long id, String sysName, String orgName, String path, Long fileSize, String fileType, LocalDateTime regTime) {
        this.id = id;
        this.sysName = sysName;
        this.orgName = orgName;
        this.path = path;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.regTime = regTime;
    }

    @Override
    public String toString() {
        return "FileDto{" +
                "id=" + id +
                ", sysName='" + sysName + '\'' +
                ", orgName='" + orgName + '\'' +
                ", path='" + path + '\'' +
                ", fileSize=" + fileSize +
                ", fileType='" + fileType + '\'' +
                ", regTime=" + regTime +
                '}';
    }
}
