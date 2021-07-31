package com.ssafy.study_with_us.domain.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
@Table(name="study_member_ref")
public class StudyMemberRef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_member_ref_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    public StudyMemberRef() {
    }

    @Builder
    public StudyMemberRef(Long id, Member member, Study study) {
        this.id = id;
        this.member = member;
        this.study = study;
    }

    @Override
    public String toString() {
        return "StudyMemberRef{" +
                "id=" + id +
                ", member=" + member +
                ", study=" + study +
                '}';
    }
}
