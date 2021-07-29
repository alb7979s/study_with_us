package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.dto.FileDto;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    
    // 받는거 예시
    @PostMapping
    public Object fileReqTest(FileDto fileDto){
        // file 있을시 처리
        if (fileDto.getFiles() != null) {
            MultipartFile mf = fileDto.getFiles().get(0);
        }
        // data 처리
        JSONObject jObject = new JSONObject(fileDto.getJsonData());
        // jObject 가지고 멤버냐, 스터디냐에 따라서 처리해줌
        System.out.println("fileDto = " + fileDto);
        System.out.println("jObject.get(\"image\") = " + jObject.get("image"));
        return null;
//        return studyProfileService.create(params);
    }

}
