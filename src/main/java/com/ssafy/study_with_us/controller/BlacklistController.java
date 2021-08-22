package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.dto.IdReqDto;
import com.ssafy.study_with_us.error.ErrorResponse;
import com.ssafy.study_with_us.error.exception.ErrorCode;
import com.ssafy.study_with_us.response.ApiResult;
import com.ssafy.study_with_us.response.ResponseMessage;
import com.ssafy.study_with_us.response.StatusCode;
import com.ssafy.study_with_us.service.BlacklistService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("blacklist")
public class BlacklistController {
    private final BlacklistService blacklistService;

    public BlacklistController(BlacklistService blacklistService) {
        this.blacklistService = blacklistService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Object addBlacklist(@RequestBody IdReqDto params) throws AuthenticationException {
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.ADDED_BLACKLIST).dataType("blacklist").data(blacklistService.addBlacklist(params)).build();
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Object deleteBlacklist(@RequestBody IdReqDto params) throws AuthenticationException {
        blacklistService.deleteBlacklist(params);
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.DELETED_BLACKLIST).build();
    }

    @GetMapping
    public Object getBlacklist(){
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.SEARCHED_BLACKLIST).dataType("blacklist_studies")
                .data(blacklistService.getBlacklist()).build();
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<ErrorResponse> handleAuthenticationException(Exception e) {
        final ErrorResponse response = ErrorResponse.of(ErrorCode.BLACKLIST_ACCESS_DENIED);
        log.error("해당 권한은 스터디장만 가능합니다.", e);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
