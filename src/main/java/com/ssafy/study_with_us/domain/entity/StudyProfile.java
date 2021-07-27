package com.ssafy.study_with_us.domain.entity;

import lombok.Builder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "study_profile")
@DiscriminatorValue("study")
public class StudyProfile extends Profile{

    public StudyProfile() {
    }

    @Builder
    public StudyProfile(Long id, String image, String thumbnail, String path) {
        super(id, image, thumbnail, path);
    }
}
