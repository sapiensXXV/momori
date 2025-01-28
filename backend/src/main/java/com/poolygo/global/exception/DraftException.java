package com.poolygo.global.exception;

public class DraftException extends RuntimeException {

    private final int code;
    private final String message;

    public DraftException(ExceptionCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }
}
