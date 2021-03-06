package com.ssafy.study_with_us.domain.repository;

import com.ssafy.study_with_us.domain.entity.StudyMemberRef;
import com.ssafy.study_with_us.dto.IdReqDto;

import java.util.List;

public interface StudyMemberRefRepositoryCustom {
    Object withdraw(IdReqDto params);
    StudyMemberRef getStudyMember(Long memberId, Long studyId);
    List<StudyMemberRef> getRecentlyStudies(Long memberId);
    List<StudyMemberRef> getConnectionList(Long studyId);

}
