package com.ssafy.study_with_us.dto;

import com.ssafy.study_with_us.domain.entity.Theme;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ThemesReqDto {
    private List<String> themes;
    private Integer page;

    public ThemesReqDto() {
    }

    @Builder
    public ThemesReqDto(List<String> themes, Integer page) {
        this.themes = themes;
        this.page = page;
    }

    @Override
    public String toString() {
        return "ThemesReqDto{" +
                "themes=" + themes +
                ", page=" + page +
                '}';
    }
}
