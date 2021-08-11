package com.ssafy.study_with_us.domain.repository;

import com.ssafy.study_with_us.domain.entity.StudyTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface StudyTimeRepository extends JpaRepository<StudyTime, Long> {
    StudyTime save(StudyTime studyTime);
    StudyTime getStudyTimeByMember_IdAndStudyDate(Long memberId, LocalDate studyDate);
    List<StudyTime> getByMemberId(Long memberId);
}
