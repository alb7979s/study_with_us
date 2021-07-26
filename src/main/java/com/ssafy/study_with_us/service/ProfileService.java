package com.ssafy.study_with_us.service;

import com.ssafy.study_with_us.domain.entity.Profile;
import com.ssafy.study_with_us.domain.repository.ProfileRepository;
import com.ssafy.study_with_us.dto.ProfileDto;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Object create(ProfileDto params){
        profileRepository.save(Profile.builder()
                .id(params.getId())
                .image(params.getImage())
//                .thumbnail() 썸네일도 따로 처리 해줘야함 할게 많네...?
                .path(params.getPath())
                .build());
        return null;
    }
}
