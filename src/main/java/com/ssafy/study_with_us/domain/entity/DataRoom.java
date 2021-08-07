package com.ssafy.study_with_us.domain.entity;

import com.ssafy.study_with_us.dto.DataRoomDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "data_room")
public class DataRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "data_room_id")
    private Long id;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "study_id")
    private Study study;

    public DataRoom() {
    }

    @Builder
    public DataRoom(Long id, String subject, String content, Member member, Study study) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.member = member;
        this.study = study;
    }

    public DataRoomDto entityToDto(){
        return DataRoomDto.builder().id(id).subject(subject).content(content).memberId(member.getId()).studyId(study.getId()).build();
    }
}
