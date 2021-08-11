package com.ssafy.study_with_us.domain.entity;

import com.ssafy.study_with_us.dto.StudyTimeDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "study_time")
public class StudyTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_time_id")
    private Long id;

    @Column(name = "study_date")
    private LocalDate studyDate;

    @Column(name = "study_time")
    private Long studyTime;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public StudyTime() {
    }

    @Builder
    public StudyTime(Long id, LocalDate studyDate, Long studyTime, Member member) {
        this.id = id;
        this.studyDate = studyDate;
        this.studyTime = studyTime;
        this.member = member;
    }

    public StudyTimeDto entityToDto(){
        return StudyTimeDto.builder().studyTimeId(id).studyDate(studyDate).studyTime(studyTime).memberId(member.getId()).build();
    }
}
