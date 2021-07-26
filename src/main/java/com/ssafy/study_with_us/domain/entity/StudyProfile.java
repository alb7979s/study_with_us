package com.ssafy.study_with_us.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "study_profile")
@DiscriminatorValue("study")
public class StudyProfile extends Profile{

    public StudyProfile() {
    }
}
