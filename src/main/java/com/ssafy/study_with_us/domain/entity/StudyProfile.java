package com.ssafy.study_with_us.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("study")
public class StudyProfile extends Profile{

    public StudyProfile() {
    }
}
