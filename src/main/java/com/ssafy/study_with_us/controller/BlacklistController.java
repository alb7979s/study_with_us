package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.dto.IdReqDto;
import com.ssafy.study_with_us.service.BlacklistService;
import com.ssafy.study_with_us.util.response.ApiResult;
import com.ssafy.study_with_us.util.response.ResponseMessage;
import com.ssafy.study_with_us.util.response.StatusCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("blacklist")
public class BlacklistController {
    private final BlacklistService blacklistService;

    public BlacklistController(BlacklistService blacklistService) {
        this.blacklistService = blacklistService;
    }

    @PostMapping
    public Object addBlacklist(@RequestBody IdReqDto params){
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.ADDED_BLACKLIST).dataType("blacklist").data(blacklistService.addBlacklist(params)).build();
    }

    @DeleteMapping
    public Object deleteBlacklist(){
        
    }
}
