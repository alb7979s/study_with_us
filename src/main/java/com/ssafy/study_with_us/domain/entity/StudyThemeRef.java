package com.ssafy.study_with_us.domain.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "stduy_theme_ref")
public class StudyThemeRef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_theme_ref_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "study_id")
    private Study study;

    @ManyToOne
    @JoinColumn(name = "theme_name")
    private Theme theme;

    public StudyThemeRef() {
    }

    @Builder
    public StudyThemeRef(Long id, Study study, Theme theme) {
        this.id = id;
        this.study = study;
        this.theme = theme;
    }

}
