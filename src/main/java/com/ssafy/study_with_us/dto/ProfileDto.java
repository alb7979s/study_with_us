package com.ssafy.study_with_us.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProfileDto {
    private Long id;
    private String image;
    private String thumbnail;
    private String path;

}
