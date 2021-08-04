package com.ssafy.study_with_us.domain.repository;

import com.ssafy.study_with_us.domain.entity.Tomato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TomatoRepository extends JpaRepository<Tomato, Long> {

}
