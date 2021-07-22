package com.ssafy.study_with_us.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/study")
public class StudyController {

    @PostMapping
    public Object Create(){
        return null;
    }

}
