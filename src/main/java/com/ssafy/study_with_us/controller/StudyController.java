package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.dto.StudyDto;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/study")
public class StudyController {

    @PostMapping
    public Object Create(@RequestBody StudyDto studyDto){

        return null;
    }

}
