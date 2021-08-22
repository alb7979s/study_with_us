package com.ssafy.study_with_us.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", "Method Not Allowed"),
    ENTITY_NOT_FOUND(400, "C003", "Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
    INVALID_TYPE_VALUE(400, "C005", "Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "C006", "접근이 거부되었습니다."),
    ACCESS_DENIED(400, "C007", "작성자만 수정/삭제 가능합니다."),

    // Member
    EMAIL_DUPLICATION(400, "M001", "이메일이 중복되었습니다."),
    FILE_FORMAT_ERROR(400, "M002", "파일 형식이 맞지 않습니다."),
    EMAIL_NOT_FOUNDED(400, "M003", "이메일을 찾을 수 없습니다."),

    // Profile
    PROFILE_NOT_FOUNDED(400, "P001", "프로필이 존재하지 않습니다."),

    // File
    FILE_NOT_FOUNDED(400, "F001", "파일이 존재하지 않습니다."),

    //blacklist
    BLACKLIST_ACCESS_DENIED(400, "B001", "해당 권한은 스터디장만 가능합니다."),

    ;
    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }


}
