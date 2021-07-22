package com.ssafy.study_with_us.domain.entity;


import javax.persistence.*;

@Entity
@Table(name = "study")
@DiscriminatorValue("study")
public class Study extends Profile{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    private Long id;

    @Column(name = "study_name", length = 30, nullable = false)
    private String studyName;

    @Column(name = "study_intro", columnDefinition = "TEXT")
    private String studyIntro;

    @Column(name = "study_leader", nullable = false)
    private String studyLeader;

    @Column(name = "security", nullable = false)
    private String security;

    public Study() {
    }

    public Study(Long id, String studyName, String studyIntro, String studyLeader, String security) {
        this.id = id;
        this.studyName = studyName;
        this.studyIntro = studyIntro;
        this.studyLeader = studyLeader;
        this.security = security;
    }

    @Override
    public String toString() {
        return "Studydetail{" +
                "id=" + id +
                ", studyName='" + studyName + '\'' +
                ", studyIntro='" + studyIntro + '\'' +
                ", studyLeader='" + studyLeader + '\'' +
                ", security='" + security + '\'' +
                '}';
    }
}
