package com.ssafy.study_with_us.domain.repository;

import com.ssafy.study_with_us.domain.entity.Study;
import com.ssafy.study_with_us.domain.entity.StudyProfile;
import com.ssafy.study_with_us.domain.entity.StudyThemeRef;
import com.ssafy.study_with_us.domain.entity.Theme;
import com.ssafy.study_with_us.dto.StudyDto;

import java.util.List;

public interface StudyRepositoryCustom {
    List<Theme> getThemes();
    List<StudyThemeRef> getThemes(Long studyId);
    Study getProfile(Long studyId);
    Object update(StudyDto params);
}
