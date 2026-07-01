package com.garagehub.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    TEST(HttpStatus.BAD_REQUEST, "phone", "X000", "테스트 에러입니다."),

    // 중복
    DUPLICATE_PHONE(HttpStatus.BAD_REQUEST, "D001", "phone", "이미 가입된 전화번호입니다."),

    // 인증
    SMS_LOCK(HttpStatus.BAD_REQUEST, "A001", "phone", "요청을 처리 중입니다. 잠시 후 다시 시도해주세요."),
    SMS_COOLDOWN(HttpStatus.BAD_REQUEST, "A002", "phone", "잠시 후 다시 시도해주세요."),

    // SMS
    SMS_SEND_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "S001", "phone", "인증번호 발송에 실패했습니다.");

    private final HttpStatus status;
    private final String field;
    private final String code;
    private final String message;

}