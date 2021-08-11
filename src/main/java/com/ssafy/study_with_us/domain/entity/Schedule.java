package com.ssafy.study_with_us.domain.entity;

import com.ssafy.study_with_us.dto.ScheduleDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @Column(name = "schedule_date")
    private LocalDate scheduleDate;

    @Column(name = "info", nullable = false)
    private String info;

    @ManyToOne
    @JoinColumn(name = "study_id")
    private Study study;

    public Schedule() {
    }

    @Builder
    public Schedule(Long id, LocalDate scheduleDate, String info, Study study) {
        this.id = id;
        this.scheduleDate = scheduleDate;
        this.info = info;
        this.study = study;
    }
    public ScheduleDto entityToDto(){
        return ScheduleDto.builder().scheduleId(id).scheduleDate(scheduleDate).info(info).studyId(study.getId()).build();
    }
}
