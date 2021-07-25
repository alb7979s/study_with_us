package com.ssafy.study_with_us.domain.entity;


import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authority")
@Getter
public class Authority {

    @Id
    @Column(name = "authority_name", length = 50)
    private String authorityName;

    public Authority() {}
    @Builder
    public Authority(String authorityName) {
        this.authorityName = authorityName;
    }
}