package com.ssafy.study_with_us.domain.repository;

public interface BlacklistRepositoryCustom {
    void delete(Long studyId, Long memberId);
}
