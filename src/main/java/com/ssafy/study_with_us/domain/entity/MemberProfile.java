package com.ssafy.study_with_us.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("member")
public class MemberProfile extends Profile{

    public MemberProfile() {
    }
}
