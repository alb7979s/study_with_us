package com.ssafy.study_with_us.domain.entity;

import com.ssafy.study_with_us.dto.FileDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "file")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @Column(name = "sys_name")
    private String sysName;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "path")
    private String path;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "reg_time")
    private LocalDateTime regTime;

    @ManyToOne
    @JoinColumn(name = "data_room_id")
    private DataRoom dataRoom;

    public FileEntity() {
    }

    @Builder
    public FileEntity(Long id, String sysName, String orgName, String path, Long fileSize, String fileType, LocalDateTime regTime, DataRoom dataRoom) {
        this.id = id;
        this.sysName = sysName;
        this.orgName = orgName;
        this.path = path;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.regTime = regTime;
        this.dataRoom = dataRoom;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", sysName='" + sysName + '\'' +
                ", orgName='" + orgName + '\'' +
                ", path='" + path + '\'' +
                ", fileSize=" + fileSize +
                ", fileType='" + fileType + '\'' +
                ", regTime=" + regTime +
                ", dataRoom=" + dataRoom +
                '}';
    }

    public FileDto entityToDto(){
        return FileDto.builder().id(id).sysName(sysName).orgName(orgName).path(path).fileSize(fileSize).fileType(fileType).regTime(regTime).build();
    }
}
