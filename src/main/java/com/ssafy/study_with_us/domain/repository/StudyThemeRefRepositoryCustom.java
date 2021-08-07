package com.ssafy.study_with_us.domain.repository;

import java.util.List;

public interface StudyThemeRefRepositoryCustom {
    List<Long> searchStudyByThemes(List<String> themes, Integer page);
}
