package com.ssafy.study_with_us.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiResult<T> {
    private int status;
    private String message;
    private String dataType;
    private T data;

    public ApiResult() {
    }

    @Builder
    public ApiResult(int status, String message, String dataType, T data) {
        this.status = status;
        this.message = message;
        this.dataType = dataType;
        this.data = data;
    }
}
