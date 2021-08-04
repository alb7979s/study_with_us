package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.service.TomatoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tomato")
public class TomatoController {
    private final TomatoService tomatoService;

    public TomatoController(TomatoService tomatoService) {
        this.tomatoService = tomatoService;
    }
}
