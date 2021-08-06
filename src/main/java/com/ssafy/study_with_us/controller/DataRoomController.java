package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.dto.DataRoomDto;
import com.ssafy.study_with_us.dto.FileReqDto;
import com.ssafy.study_with_us.service.DataRoomService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dataroom")
public class DataRoomController {
    private final DataRoomService dataRoomService;

    public DataRoomController(DataRoomService dataRoomService) {
        this.dataRoomService = dataRoomService;
    }

    @PostMapping
    public Object create(FileReqDto params){
        return null;
    }

    private DataRoomDto getDataRoomDtoAtFile(FileReqDto params){
        return null;
    }
}
