package com.ssafy.study_with_us.service;

import com.ssafy.study_with_us.domain.entity.StudyProfile;
import com.ssafy.study_with_us.domain.repository.StudyProfileRepository;
import com.ssafy.study_with_us.dto.ProfileDto;
import org.springframework.stereotype.Service;

@Service
public class StudyProfileService {

    private StudyProfileRepository studyProfileRepository;

    public StudyProfileService(StudyProfileRepository studyProfileRepository) {
        this.studyProfileRepository = studyProfileRepository;
    }

    public Object create(ProfileDto params) {
        studyProfileRepository.save(StudyProfile.builder()
                .id(params.getId())
                .image(params.getImage())
                .path(params.getPath())
                .thumbnail(params.getThumbnail())
                .build());
        return null;
    }

}
