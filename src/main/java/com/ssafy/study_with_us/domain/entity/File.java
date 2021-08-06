package com.ssafy.study_with_us.domain.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class File {

    @Column(name = "sys_name")
    private String sysName;
    @Column(name = "org_name")
    private String orgName;
    @Column(name = "path")
    private String path;
    @Column(name = "reg_time")
    private LocalDateTime regTime;

    public File() {
    }

    public File(String sysName, String orgName, String path, LocalDateTime regTime) {
        this.sysName = sysName;
        this.orgName = orgName;
        this.path = path;
        this.regTime = regTime;
    }

    @Override
    public String toString() {
        return "File{" +
                "sysName='" + sysName + '\'' +
                ", orgName='" + orgName + '\'' +
                ", path='" + path + '\'' +
                ", regTime=" + regTime +
                '}';
    }
}
