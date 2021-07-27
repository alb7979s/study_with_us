package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.dto.StudyDto;
import com.ssafy.study_with_us.service.StudyService;
import org.springframework.web.bind.annotation.*;

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
//      create 호출하는 사람, 즉 만드는 사람이 leader
        Map<String, Object> map = new HashMap<>();
        map.put("result", studyService.create(params));
        return map;
    }

    @PatchMapping
    public Object update(@RequestBody StudyDto params){
        return studyService.update(params);
    }

    @GetMapping
    public Object read(@RequestParam Long id){
        return studyService.read(id);
    }
}
