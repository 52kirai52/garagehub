package com.garagehub.global.exception;

public class SmsException extends RuntimeException {
    public SmsException(String message) {
        super(message);
    }
}