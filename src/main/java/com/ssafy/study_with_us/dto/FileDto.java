package com.ssafy.study_with_us.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class FileDto {
    private Long id;
    private String sysName;
    private String orgName;
    private String path;
    private Long fileSize;
    private String contentType;
    private LocalDateTime regTime;
    private DataRoomDto dataRoom;

    public FileDto() {
    }

    @Builder
    public FileDto(Long id, String sysName, String orgName, String path, Long fileSize, String contentType, LocalDateTime regTime, DataRoomDto dataRoom) {
        this.id = id;
        this.sysName = sysName;
        this.orgName = orgName;
        this.path = path;
        this.fileSize = fileSize;
        this.contentType = contentType;
        this.regTime = regTime;
        this.dataRoom = dataRoom;
    }

    @Override
    public String toString() {
        return "FileDto{" +
                "id=" + id +
                ", sysName='" + sysName + '\'' +
                ", orgName='" + orgName + '\'' +
                ", path='" + path + '\'' +
                ", fileSize=" + fileSize +
                ", contentType='" + contentType + '\'' +
                ", regTime=" + regTime +
                ", dataRoom=" + dataRoom +
                '}';
    }
}
