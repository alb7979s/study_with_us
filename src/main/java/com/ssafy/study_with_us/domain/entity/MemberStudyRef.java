package com.ssafy.study_with_us.domain.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name="member_study_ref")
public class MemberStudyRef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_study_ref_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "study_id")
    private Study study;

    public MemberStudyRef() {
    }

    @Builder
    public MemberStudyRef(Long id, Member member, Study study) {
        this.id = id;
        this.member = member;
        this.study = study;
    }

    @Override
    public String toString() {
        return "MemberStudyRef{" +
                "id=" + id +
                ", member=" + member +
                ", study=" + study +
                '}';
    }
}
