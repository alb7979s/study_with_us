package com.ssafy.study_with_us.dto;

import lombok.*;

@Getter
public class ProfileDto {
    private Long id;
    private String image;
    private String imageOrgName;
    private String thumbnail;
    private String path;

    public ProfileDto() {
    }
    @Builder
    public ProfileDto(Long id, String image, String imageOrgName, String thumbnail, String path) {
        this.id = id;
        this.image = image;
        this.imageOrgName = imageOrgName;
        this.thumbnail = thumbnail;
        this.path = path;
    }

    @Override
    public String toString() {
        return "ProfileDto{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", imageOrgName='" + imageOrgName + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
