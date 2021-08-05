package com.ssafy.study_with_us.domain.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
public class Tomato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tomato_id")
    private Long id;

    @Column(name = "tomato_count")
    private Integer tomatoCount;

    @Column(name = "tomato_date")
    private LocalDate tomatoDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "study_id")
    private Study study;

    public Tomato() {
    }

    @Builder
    public Tomato(Long id, Integer tomatoCount, LocalDate tomatoDate, Member member, Study study) {
        this.id = id;
        this.tomatoCount = tomatoCount;
        this.tomatoDate = tomatoDate;
        this.member = member;
        this.study = study;
    }
}
