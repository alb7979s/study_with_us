package com.ssafy.study_with_us.domain.repository;

import com.ssafy.study_with_us.domain.entity.StudyMemberRef;
import com.ssafy.study_with_us.domain.entity.StudyThemeRef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyMemberRefRepository extends JpaRepository<StudyMemberRef, Long>, StudyMemberRefRepositoryCustom {
    StudyThemeRef save(StudyThemeRef studyThemeRef);
}
