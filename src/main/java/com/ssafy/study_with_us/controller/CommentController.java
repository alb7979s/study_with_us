package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.dto.CommentDto;
import com.ssafy.study_with_us.dto.IdReqDto;
import com.ssafy.study_with_us.service.CommentService;
import com.ssafy.study_with_us.util.response.ApiResult;
import com.ssafy.study_with_us.util.response.ResponseMessage;
import com.ssafy.study_with_us.util.response.StatusCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Object create(@RequestBody CommentDto params){
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.CREATED_COMMENT).dataType("comment").data(commentService.create(params)).build();
    }
    @PatchMapping
    public Object update(@RequestBody CommentDto params)
    {
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.UPDATED_COMMENT).dataType("comment").data(commentService.update(params)).build();
    }
    @DeleteMapping
    public Object delete(@RequestBody CommentDto params) {
        commentService.delete(params);
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.DELETED_COMMENT).build();
    }
    @GetMapping
    public Object getComments(@RequestParam Long studyId) {
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.SEARCHED_COMMENTS).dataType("comments").data(commentService.getComments(studyId)).build();
    }
}
