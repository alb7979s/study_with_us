package com.ssafy.study_with_us.domain.repository;

import com.ssafy.study_with_us.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Schedule save(Schedule schedule);
    void delete(Schedule schedule);
    List<Schedule> getScheduleByStudyIdAndScheduleDateBetween(Long studyId, LocalDate startDate, LocalDate endTime);
}
