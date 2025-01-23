package com.poolygo.global.exception;

public class AuthException extends RuntimeException {
    private final int code;
    private final String message;

    public AuthException(ExceptionCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }
}
