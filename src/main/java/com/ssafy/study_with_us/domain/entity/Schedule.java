package com.ssafy.study_with_us.domain.entity;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;
    @Column
    private LocalDate date;
    @Column(length = 30, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
//  N:1
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    public Schedule() {
    }

    public Schedule(Long id, LocalDate date, String title, String content, Study study) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.content = content;
        this.study = study;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", study=" + study +
                '}';
    }
}
