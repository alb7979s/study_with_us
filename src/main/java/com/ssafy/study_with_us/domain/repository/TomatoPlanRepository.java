package com.ssafy.study_with_us.domain.repository;

import com.ssafy.study_with_us.domain.entity.TomatoPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TomatoPlanRepository extends JpaRepository<TomatoPlan, Long> {
    TomatoPlan save(TomatoPlan tomatoPlan);
    TomatoPlan getById(Long tomatoPlanId);
}
