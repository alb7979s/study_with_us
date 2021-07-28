package com.ssafy.study_with_us.domain.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    private Long id;

    @Column(name = "study_name")
    private String studyName;
    @Column(name = "study_intro", columnDefinition = "TEXT")
    private String studyIntro;
    @Column(name = "study_leader")
    private Long studyLeader;
    @Column(name = "security")
    private String security;

//  얘도 방향관계 확실치 않아서 생성자에 추가 안했어요
    @OneToOne
    @JoinColumn(name = "profile_id")
    private StudyProfile profile;

    public Study() {
    }

    @Builder
    public Study(Long id, String studyName, String studyIntro, Long studyLeader, String security, StudyProfile profile) {
        this.id = id;
        this.studyName = studyName;
        this.studyIntro = studyIntro;
        this.studyLeader = studyLeader;
        this.security = security;
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "Study{" +
                "id=" + id +
                ", studyName='" + studyName + '\'' +
                ", studyIntro='" + studyIntro + '\'' +
                ", studyLeader=" + studyLeader +
                ", security='" + security + '\'' +
                ", profile=" + profile +
                '}';
    }
}
