package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.dto.ProfileDto;
import com.ssafy.study_with_us.service.ProfileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProflieController {
    private ProfileService profileService;

    public ProflieController(ProfileService profileService) {
        this.profileService = profileService;
    }

//  return 나중에 싹 다 정리해줘야함
    @PostMapping
    public Object create(@RequestBody ProfileDto params){
        return profileService.create(params);
    }
}
