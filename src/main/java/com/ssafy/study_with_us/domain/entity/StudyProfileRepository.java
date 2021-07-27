package com.ssafy.study_with_us.domain.entity;

import com.ssafy.study_with_us.dto.ProfileDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyProfileRepository extends JpaRepository<StudyProfile, Long> {
    StudyProfile save(StudyProfile studyProfile);
}
