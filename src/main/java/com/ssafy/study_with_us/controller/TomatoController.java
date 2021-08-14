package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.dto.TomatoDto;
import com.ssafy.study_with_us.dto.TomatoPlanDto;
import com.ssafy.study_with_us.service.TomatoService;
import com.ssafy.study_with_us.response.ApiResult;
import com.ssafy.study_with_us.response.ResponseMessage;
import com.ssafy.study_with_us.response.StatusCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tomato")
public class TomatoController {
    private final TomatoService tomatoService;

    public TomatoController(TomatoService tomatoService) {
        this.tomatoService = tomatoService;
    }

//  토마토 추가
//  if 해당 날짜, 해당 멤버, 해당 스터디에 준하는 토마토 테이블 있을시: 토마토 카운트 갱신
//  else: 1짜리 토마토 생성
//  0짜리 토마토 다 만들어 놓는거보다 첫 추가시에 만들어주는게 효율적일듯
    @PostMapping
    public Object addTomato(@RequestBody TomatoDto params){
        return ApiResult.builder().status(StatusCode.OK).message("토마토 추가 성공").dataType("tomato")
                .data(tomatoService.addTomato(params)).build();
    }
    
    // 일단 토마토 테스트 할라고 여기 만들었는데, 멤버, 스터디 세부 검색 하는곳으로 이동 예정
    @GetMapping("/study/{studyId}")
    public Object getTomatoes(@PathVariable("studyId") Long studyId){
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.SEARCHED_STUDY_TOMATOES).dataType("tomatoes_by_study").data(tomatoService.getTomatoes(TomatoDto.builder().studyId(studyId).build())).build();
    }
    @GetMapping("/today/study/{studyId}")
    public Object getTodayTomatoes(@PathVariable("studyId") Long studyId){
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.SEARCHED_STUDY_TOMATOES).dataType("tomatoes_by_today_study").data(tomatoService.getTodayTomatoes(TomatoDto.builder().studyId(studyId).build())).build();
    }
    @GetMapping
    public Object getTomatoes(){
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.SEARCHED_MEMBER_TOMATOES).dataType("tomatoes_by_member").data(tomatoService.getTomatoes()).build();
    }

    @PostMapping("/goal")
    public Object addGoal(@RequestBody TomatoPlanDto params){
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.CREATED_STUDY_TOMATO_GOAL).dataType("tomato_plan")
                .data(tomatoService.addGoal(params)).build();
    }
    @PatchMapping("/goal")
    public Object updateGoal(@RequestBody TomatoPlanDto params){
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.UPDATED_STUDY_TOMATO_GOAL).dataType("tomato_plan")
                .data(tomatoService.updateGoal(params)).build();
    }
    @GetMapping("/goal/{studyId}")
    public Object getGoal(@PathVariable("studyId") Long studyId){
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.SEARCHED_STUDY_TOMATO_GOAL).dataType("tomato_plans")
                .data(tomatoService.getGoal(studyId)).build();
    }
}
