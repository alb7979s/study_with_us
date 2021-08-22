package com.ssafy.study_with_us.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FileReqDto {
    private List<MultipartFile> files = new ArrayList<>();
    private String jsonData;

    public FileReqDto() {
    }

    @Builder
    public FileReqDto(List<MultipartFile> files, String jsonData) {
        this.files = files;
        this.jsonData = jsonData;
    }

    @Override
    public String toString() {
        return "FileDto{" +
                "files=" + files +
                ", jsonData='" + jsonData + '\'' +
                '}';
    }
}
