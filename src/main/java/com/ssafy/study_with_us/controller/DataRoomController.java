package com.ssafy.study_with_us.controller;

import com.ssafy.study_with_us.dto.DataRoomDto;
import com.ssafy.study_with_us.dto.FileReqDto;
import com.ssafy.study_with_us.service.DataRoomService;
import com.ssafy.study_with_us.response.ApiResult;
import com.ssafy.study_with_us.response.ResponseMessage;
import com.ssafy.study_with_us.response.StatusCode;
import org.apache.tomcat.websocket.AuthenticationException;
import org.json.JSONObject;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Object create(FileReqDto params) throws IOException {
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.CREATED_DATA_ROOM).dataType("data_room")
        .data(dataRoomService.create(getDataRoomDtoAtFile(params), params.getFiles())).build();
    }
    @PatchMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Object update(FileReqDto params) throws IOException, AuthenticationException {
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.UPDATED_DATA_ROOM).dataType("data_room")
        .data(dataRoomService.update(getDataRoomDtoAtFile(params), params.getFiles())).build();
    }

    @GetMapping("/detail/{dataRoomId}")
    public Object getDetail(@PathVariable("dataRoomId") Long dataRoomId){
        return ApiResult.builder().status(StatusCode.OK).message(ResponseMessage.SEARCHED_DATA_ROOM_DETAIL).dataType("data_room").data(dataRoomService.getDetail(dataRoomId)).build();
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
