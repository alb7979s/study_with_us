package com.ssafy.study_with_us.domain.repository;

import com.ssafy.study_with_us.domain.entity.StudyThemeRef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyThemeRefRepository extends JpaRepository<StudyThemeRef, Long>, StudyThemeRefRepositoryCustom {
    StudyThemeRef save(StudyThemeRef studyThemeRef);
}
