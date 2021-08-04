package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.domain.entity.Profile;
import com.ssafy.study_with_us.dto.FileDto;
import com.ssafy.study_with_us.dto.IdReqDto;
import com.ssafy.study_with_us.dto.StudyDto;
import com.ssafy.study_with_us.service.ProfileService;
import com.ssafy.study_with_us.service.StudyService;
import com.ssafy.study_with_us.util.response.ApiResult;
import com.ssafy.study_with_us.util.response.ResponseMessage;
import com.ssafy.study_with_us.util.response.StatusCode;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping("/study")
public class StudyController {
    private final StudyService studyService;
    private final ProfileService profileService;

    public StudyController(StudyService studyService, ProfileService profileService) {
        this.studyService = studyService;
        this.profileService = profileService;
    }

    // 멤버가 직접 가입 하는거
    @PostMapping("/join")
    public Object join(@RequestBody IdReqDto params){
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.CREATED_STUDY_MEMBER).dataType("study_member_ref").data(studyService.joinMember(params)).build();
    }
    @PostMapping("/invite")
    public Object inviteMember(@RequestBody IdReqDto params){
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.CREATED_STUDY_MEMBER).dataType("study_member_ref").data(studyService.joinMember(params)).build();
    }
    @DeleteMapping("/withdraw")
    public Object withdraw(@RequestBody IdReqDto params){
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.DELETED_STUDY_MEMBER).dataType("Long").data(studyService.withdraw(params)).build();
    }
    @PostMapping
    public Object create(FileDto params) throws IOException {
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.CREATED_STUDY).dataType("study")
                .data(studyService.create(getStudyDtoAtFile(params))).build();
    }

    @PatchMapping
    public Object update(FileDto params) throws IOException {
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.UPDATED_STUDY)
                .data(studyService.update(getStudyDtoAtFile(params))).build();
    }

    @GetMapping("/detail")
    public Object getDetail(@RequestParam Long id){
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.SEARCHED_STUDY).dataType("study").data(studyService.getDetail(id)).build();
    }
    @GetMapping
    public Object getStudyList(){
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.SEARCHED_STUDY).dataType("studies").data(studyService.getStudyList()).build();
    }

    @PostMapping("/search")
    public Object searchStudyByThemes(@RequestBody Map<String, List<String>> params){
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.SEARCHED_STUDY_THEMES).dataType("themes").data(studyService.searchStudyByThemes(params.get("themes"))).build();
    }

    private StudyDto getStudyDtoAtFile(FileDto params) throws IOException {
        Profile profile = null;
        // 파일 정보 있으면 받은 정보로 생성
        if (params.getFiles() != null) {
            profile = profileService.studyProfileCreate(params.getFiles().get(0));
        }
        // study
        JSONObject jObject = new JSONObject(params.getJsonData());
        Set<String> themes = new HashSet<>();
        //themes
        if (jObject.has("themes")){
            for (Object theme : jObject.getJSONArray("themes")) {
                themes.add((String) theme);
            }
        };
        return StudyDto.builder()
                .id(jObject.has("id") ? jObject.getLong("id") : null)
                .studyName(jObject.has("studyName") ? jObject.getString("studyName") : null)
                .studyIntro(jObject.has("studyIntro") ? jObject.getString("studyIntro") : null)
                .security(jObject.has("security") ? jObject.getString("security") : null)
                .themes(themes)
                .profile(profile)
                .build();
    }
}
