package com.garagehub.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    TEST(ErrorStatus.CLIENT_ERROR, "phone", "X000", "테스트 에러입니다.");

    private final ErrorStatus status;
    private final String field;
    private final String code;
    private final String message;

    @Getter
    @RequiredArgsConstructor
    public enum ErrorStatus {
        CLIENT_ERROR(HttpStatus.BAD_REQUEST),
        SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR);

        private final HttpStatus httpStatus;
    }
}