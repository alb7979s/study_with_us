package com.ssafy.study_with_us.domain.repository;

import com.ssafy.study_with_us.dto.IdReqDto;

public interface StudyMemberRefRepositoryCustom {
    Object withdraw(IdReqDto params);
}
