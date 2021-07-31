package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.domain.entity.Profile;
import com.ssafy.study_with_us.dto.FileDto;
import com.ssafy.study_with_us.dto.IdReqDto;
import com.ssafy.study_with_us.dto.StudyDto;
import com.ssafy.study_with_us.service.ProfileService;
import com.ssafy.study_with_us.service.StudyService;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("/study")
public class StudyController {
    private StudyService studyService;
    private ProfileService profileService;

    public StudyController(StudyService studyService, ProfileService profileService) {
        this.studyService = studyService;
        this.profileService = profileService;
    }

    // 멤버가 직접 가입 하는거
    @PostMapping("/join")
    public Object join(@RequestBody IdReqDto params){
        return studyService.joinMember(params);
    }
    @PostMapping("/invite")
    public Object inviteMember(@RequestBody IdReqDto params){
        return studyService.joinMember(params);
    }
    @DeleteMapping("/withdraw")
    public Object withdraw(@RequestBody IdReqDto params){
        return studyService.withdraw(params);
    }
    @PostMapping
    public Object create(FileDto params) throws IOException {
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
        StudyDto studyDto = StudyDto.builder()
                .studyName(jObject.has("studyName") ? jObject.getString("studyName") : null)
                .studyIntro(jObject.has("studyIntro") ? jObject.getString("studyIntro") : null)
                .security(jObject.has("security") ? jObject.getString("security") : null)
                .themes(themes)
                .profile(profile)
                .build();

        return studyService.create(studyDto);
    }

    @PatchMapping
    public Object update(@RequestBody StudyDto params){
        return studyService.update(params);
    }

    @GetMapping
    public Object read(@RequestParam Long id){
        return studyService.read(id);
    }
}
