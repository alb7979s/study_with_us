package com.ssafy.study_with_us.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "member_profile")
@DiscriminatorValue("member")
public class MemberProfile extends Profile{

    public MemberProfile() {
        super();
    }

    public MemberProfile(Long id, String image, String thumbnail, String path){
        super(id, image, thumbnail, path);
    }
}
