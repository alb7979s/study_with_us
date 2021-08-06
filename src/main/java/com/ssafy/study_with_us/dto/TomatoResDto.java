package com.ssafy.study_with_us.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class TomatoResDto {
    private Integer totalSum;
    private Integer relevantSum;
    private List<TomatoDto> tomatoes;

    public TomatoResDto() {
    }

    @Builder
    public TomatoResDto(Integer totalSum, Integer relevantSum, List<TomatoDto> tomatoes) {
        this.totalSum = totalSum;
        this.relevantSum = relevantSum;
        this.tomatoes = tomatoes;
    }

    @Override
    public String toString() {
        return "TomatoResDto{" +
                "totalSum=" + totalSum +
                ", relevantSum=" + relevantSum +
                ", tomatoes=" + tomatoes +
                '}';
    }
}
