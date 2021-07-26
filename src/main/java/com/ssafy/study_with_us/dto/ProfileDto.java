package com.ssafy.study_with_us.dto;

import lombok.*;

@Getter
public class ProfileDto {
    private Long id;
    private String image;
    private String thumbnail;
    private String path;

    public ProfileDto() {
    }

    @Builder
    public ProfileDto(Long id, String image, String thumbnail, String path) {
        this.id = id;
        this.image = image;
        this.thumbnail = thumbnail;
        this.path = path;
    }
}
