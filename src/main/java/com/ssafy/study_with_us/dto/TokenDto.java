package com.ssafy.study_with_us.dto;

import lombok.*;

@Getter
public class TokenDto {

    private String token;

    public TokenDto() {
    }

    @Builder
    public TokenDto(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenDto{" +
                "token='" + token + '\'' +
                '}';
    }
}
