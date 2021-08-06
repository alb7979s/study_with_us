package com.ssafy.study_with_us.domain.entity;

import com.ssafy.study_with_us.dto.MemberDto;
import com.ssafy.study_with_us.dto.StudyMemberDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name = "recently_connection_time")
    private LocalDateTime recentlyConnectionTime;

    public StudyMemberRef() {
    }

    @Builder
    public StudyMemberRef(Long id, Member member, Study study, LocalDateTime recentlyConnectionTime) {
        this.id = id;
        this.member = member;
        this.study = study;
        this.recentlyConnectionTime = recentlyConnectionTime;
    }

    @Override
    public String toString() {
        return "StudyMemberRef{" +
                "id=" + id +
                ", member=" + member +
                ", study=" + study +
                ", recentlyConnectionTime=" + recentlyConnectionTime +
                '}';
    }
    public StudyMemberDto entityToDto(){
        return StudyMemberDto.builder().id(id).member(member.entityToDto())
                .study(study.entityToDto()).recentlyConnectionTime(recentlyConnectionTime).build();
    }
}
