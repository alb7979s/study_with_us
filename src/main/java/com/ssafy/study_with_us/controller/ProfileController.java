package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.domain.entity.StudyProfileService;
import com.ssafy.study_with_us.dto.ProfileDto;
import com.ssafy.study_with_us.service.ProfileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private StudyProfileService studyProfileService;

    public ProfileController(StudyProfileService studyProfileService) {
        this.studyProfileService = studyProfileService;
    }

    //  return 나중에 싹 다 정리해줘야함
    @PostMapping
    public Object create(@RequestBody ProfileDto params){
        return studyProfileService.create(params);
    }

}
