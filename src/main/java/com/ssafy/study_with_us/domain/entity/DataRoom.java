package com.ssafy.study_with_us.domain.entity;

import com.ssafy.study_with_us.dto.DataRoomDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "data_room")
public class DataRoom extends File{
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
    public DataRoom(String sysName, String orgName, String path, LocalDateTime regTime, Long id, String subject, String content, Member member, Study study) {
        super(sysName, orgName, path, regTime);
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.member = member;
        this.study = study;
    }

    private DataRoomDto entityToDto(){
        return DataRoomDto.builder().id(id).subject(subject).content(content).sysName(getSysName()).orgName(getOrgName()).path(getPath()).regTime(getRegTime())
                .member(member.entityToDto()).study(study.entityToDto()).build();
    }
}
