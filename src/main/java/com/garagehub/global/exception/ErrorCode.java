package com.garagehub.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    TEST(HttpStatus.BAD_REQUEST, "phone", "X000", "테스트 에러입니다.");

    private final HttpStatus status;
    private final String field;
    private final String code;
    private final String message;

}