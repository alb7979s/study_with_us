package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.dto.CommentDto;
import com.ssafy.study_with_us.error.ErrorResponse;
import com.ssafy.study_with_us.error.exception.ErrorCode;
import com.ssafy.study_with_us.service.CommentService;
import com.ssafy.study_with_us.response.ApiResult;
import com.ssafy.study_with_us.response.ResponseMessage;
import com.ssafy.study_with_us.response.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Object create(@RequestBody CommentDto params){
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.CREATED_COMMENT).dataType("comment").data(commentService.create(params)).build();
    }
    @PatchMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Object update(@RequestBody CommentDto params) throws AuthenticationException {
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.UPDATED_COMMENT).dataType("comment").data(commentService.update(params)).build();
    }
    @DeleteMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Object delete(@RequestBody CommentDto params) throws AuthenticationException {
        commentService.delete(params);
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.DELETED_COMMENT).build();
    }
    @GetMapping
    public Object getComments(@RequestParam Long studyId) {
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.SEARCHED_COMMENTS).dataType("comments").data(commentService.getComments(studyId)).build();
    }

}
