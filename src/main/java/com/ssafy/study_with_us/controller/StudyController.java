package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.dto.StudyDto;
import com.ssafy.study_with_us.service.StudyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/study")
public class StudyController {
    private StudyService studyService;

    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    @PostMapping
    public Object create(@RequestBody StudyDto params) {
        for (String theme : params.getThemes()) {
            System.out.println("theme = " + theme);
        }
//      create 호출하는 사람, 즉 만드는 사람이 leader
        Map<String, Object> map = new HashMap<>();
        map.put("result", studyService.create(params));
        return map;
    }

}
