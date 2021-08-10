package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.dto.IdReqDto;
import com.ssafy.study_with_us.service.BlacklistService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("blacklist")
public class BlacklistController {
    private final BlacklistService blacklistService;

    public BlacklistController(BlacklistService blacklistService) {
        this.blacklistService = blacklistService;
    }

    @PostMapping
    public Object addBlacklist(@RequestBody IdReqDto params){
        return blacklistService.addBlacklist(params);
    }
}
