package com.ssafy.study_with_us.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "member_profile")
@DiscriminatorValue("member")
public class MemberProfile extends Profile{

    public MemberProfile() {
        super();
    }

    @Builder
    public MemberProfile(Long id, String image, String imageOrgName, String thumbnail, String path) {
        super(id, image, imageOrgName, thumbnail, path);
    }
}
