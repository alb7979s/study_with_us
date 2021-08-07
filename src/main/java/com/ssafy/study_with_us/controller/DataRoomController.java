package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.dto.DataRoomDto;
import com.ssafy.study_with_us.dto.FileReqDto;
import com.ssafy.study_with_us.service.DataRoomService;
import com.ssafy.study_with_us.util.response.ApiResult;
import com.ssafy.study_with_us.util.response.ResponseMessage;
import com.ssafy.study_with_us.util.response.StatusCode;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/dataroom")
public class DataRoomController {
    private final DataRoomService dataRoomService;

    public DataRoomController(DataRoomService dataRoomService) {
        this.dataRoomService = dataRoomService;
    }

    @PostMapping
    public Object create(FileReqDto params) throws IOException {
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.CREATED_DATA_ROOM).dataType("data_room")
        .data(dataRoomService.create(getDataRoomDtoAtFile(params), params.getFiles())).build();
    }
    @PatchMapping
    public Object update(FileReqDto params) throws IOException {
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.UPDATED_DATA_ROOM).dataType("data_room")
        .data(dataRoomService.update(getDataRoomDtoAtFile(params), params.getFiles())).build();
    }

    @GetMapping("/detail")
    public Object getDetail(@RequestParam Long dataRoomId){
        dataRoomService.getDetail(dataRoomId);
        return null;
    }

    @GetMapping
    public Object getDataRoomList(@RequestParam Long studyId){
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.SEARCHED_DATA_ROOM_LIST).dataType("data_rooms").data(dataRoomService.getDataRoomList(studyId)).build();
    }

    private DataRoomDto getDataRoomDtoAtFile(FileReqDto params){
        JSONObject jObject = new JSONObject(params.getJsonData());
        return DataRoomDto.builder()
                .id(jObject.has("id") ? jObject.getLong("id") : null)
                .subject(jObject.has("subject") ? jObject.getString("subject") : null)
                .content(jObject.has("content") ? jObject.getString("content") : null)
                .studyId(jObject.has("studyId") ? jObject.getLong("studyId") : null)
                .build();
    }
}
