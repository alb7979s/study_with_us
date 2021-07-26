package com.ssafy.study_with_us.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "member_profile")
@DiscriminatorValue("member")
public class MemberProfile extends Profile{

    public MemberProfile() {
    }
}
