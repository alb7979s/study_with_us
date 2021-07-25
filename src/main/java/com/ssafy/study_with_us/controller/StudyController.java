package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.dto.StudyDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/study")
public class StudyController {
    @PostMapping
    public Object create(StudyDto params) {
        return null;
    }
}
