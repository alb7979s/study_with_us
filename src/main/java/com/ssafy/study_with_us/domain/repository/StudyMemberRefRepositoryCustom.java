package com.ssafy.study_with_us.domain.repository;

import com.ssafy.study_with_us.domain.entity.Study;
import com.ssafy.study_with_us.domain.entity.StudyMemberRef;
import com.ssafy.study_with_us.dto.IdReqDto;

import java.util.List;

public interface StudyMemberRefRepositoryCustom {
    Object withdraw(IdReqDto params);
    List<Study> getByMemberId(Long memberId, Integer page);
    StudyMemberRef getStudyMember(Long memberId, Long studyId);
    List<StudyMemberRef> getRecentlyStudies(Long memberId);
}
