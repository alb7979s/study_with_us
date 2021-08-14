package com.ssafy.study_with_us.domain.repository;

import com.ssafy.study_with_us.domain.entity.TomatoPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TomatoPlanRepository extends JpaRepository<TomatoPlan, Long> {
    TomatoPlan save(TomatoPlan tomatoPlan);
    TomatoPlan getById(Long tomatoPlanId);
    List<TomatoPlan> getByStudyId(Long studyId);
}
