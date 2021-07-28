package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.domain.entity.StudyProfileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private StudyProfileService studyProfileService;

    public ProfileController(StudyProfileService studyProfileService) {
        this.studyProfileService = studyProfileService;
    }

    @PostMapping
    public Object create(@RequestParam("profile") MultipartFile mf){
        System.out.println("mf = " + mf);
        return null;
//        return studyProfileService.create(params);
    }

}
