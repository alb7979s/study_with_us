package com.ssafy.study_with_us.domain.entity;

import com.ssafy.study_with_us.dto.TomatoPlanDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "tomato_plan")
public class TomatoPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tomato_plan_id")
    private Long id;

    @Column(name = "goal_tomato")
    private Integer goalTomato;

    @Column(name = "goal_time")
    private Integer goalTime;

    @Column(name = "tomato_date")
    private LocalDate tomatoDate;

    @ManyToOne
    @JoinColumn(name = "study_id")
    private Study study;

    public TomatoPlan() {
    }

    @Builder
    public TomatoPlan(Long id, Integer goalTomato, Integer goalTime, LocalDate tomatoDate, Study study) {
        this.id = id;
        this.goalTomato = goalTomato;
        this.goalTime = goalTime;
        this.tomatoDate = tomatoDate;
        this.study = study;
    }

    @Override
    public String toString() {
        return "TomatoPlan{" +
                "id=" + id +
                ", goalTomato=" + goalTomato +
                ", goalTime=" + goalTime +
                ", tomatoDate=" + tomatoDate +
                ", study=" + study +
                '}';
    }

    public TomatoPlanDto entityToDto(){
        return TomatoPlanDto.builder().tomatoPlanId(id).goalTomato(goalTomato).goalTime(goalTime).tomatoDate(tomatoDate).studyId(study.getId()).build();
    }
}
