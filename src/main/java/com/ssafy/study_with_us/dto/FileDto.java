package com.ssafy.study_with_us.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class FileDto {
    private List<MultipartFile> files;
    private String jsonData;

    public FileDto() {
    }

    @Builder
    public FileDto(List<MultipartFile> files, String jsonData) {
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
