package com.ssafy.study_with_us.domain.repository;

import com.ssafy.study_with_us.domain.entity.StudyProfile;
import com.ssafy.study_with_us.domain.entity.Theme;
import com.ssafy.study_with_us.dto.StudyDto;

import java.util.List;

public interface StudyRepositoryCustom {
    List<Theme> getThemes();
    List<Theme>  getThemes(Long studyId);
    StudyProfile getProfile(Long studyId);
    Object update(StudyDto params);
    void remove(String theme, Long studyID);
}
