package com.ssafy.study_with_us.domain.entity;

import com.ssafy.study_with_us.dto.StudyMemberDto;
import com.ssafy.study_with_us.dto.StudyMemberRefDto;
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

    private String nickname;

    private Boolean connected;

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
    public StudyMemberRef(Long id, String nickname, Boolean connected, Member member, Study study, LocalDateTime recentlyConnectionTime) {
        this.id = id;
        this.nickname = nickname;
        this.connected = connected;
        this.member = member;
        this.study = study;
        this.recentlyConnectionTime = recentlyConnectionTime;
    }

    public StudyMemberDto entityToDto(){
        return StudyMemberDto.builder().id(id).nickname(nickname).member(member.entityToDto())
                .study(study.entityToDto()).recentlyConnectionTime(recentlyConnectionTime).build();
    }
    public StudyMemberRefDto entityToRefDto(){
        return StudyMemberRefDto.builder().id(id).nickname(nickname).connected(connected).memberId(member.getId())
                .studyId(study.getId()).recentlyConnectionTime(recentlyConnectionTime).build();
    }
}
