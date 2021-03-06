package com.ssafy.study_with_us.domain.entity;

import com.ssafy.study_with_us.dto.StudyDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    private Long id;

    @Column(name = "study_name", nullable = false)
    private String studyName;
    @Column(name = "study_intro", columnDefinition = "TEXT")
    private String studyIntro;
    @Column(name = "study_leader")
    private Long studyLeader;
    @Column(name = "security")
    private String security;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private StudyProfile profile;

    @OneToMany(mappedBy = "study")
    private List<StudyThemeRef> themes;

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
    public StudyDto entityToDto(){
        return StudyDto.builder().id(id).studyName(studyName).studyIntro(studyIntro).studyLeader(studyLeader)
                .security(security).profile(profile == null ? null : profile.entityToDto()).build();
    }

    public List<String> getThemesString() {
        List<String> results = new ArrayList<>();
        for (StudyThemeRef theme : themes) {
            results.add(theme.getTheme().getThemeName());
        }
        return results;
    }
}
