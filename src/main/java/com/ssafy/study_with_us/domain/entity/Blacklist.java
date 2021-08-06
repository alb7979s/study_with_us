package com.ssafy.study_with_us.domain.entity;

import com.ssafy.study_with_us.dto.BlacklistDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Blacklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blacklist_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "study_id")
    private Study study;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Blacklist() {
    }

    @Builder
    public Blacklist(Long id, Study study, Member member) {
        this.id = id;
        this.study = study;
        this.member = member;
    }

    @Override
    public String toString() {
        return "Blacklist{" +
                "id=" + id +
                ", study=" + study +
                ", member=" + member +
                '}';
    }

    public BlacklistDto entityToDto(){
        return BlacklistDto.builder().id(id).study(study.entityToDto()).member(member.entityToDto()).build();
    }
}
