package com.ssafy.study_with_us.domain.repository;

import com.ssafy.study_with_us.domain.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long>, StudyRepositoryCustom {
    Study save(Study study);
}
