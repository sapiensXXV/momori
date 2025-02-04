package com.poolygo.global.exception;

public class S3Exception extends RuntimeException {
    private final int code;
    private final String message;

    public S3Exception(ExceptionCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }
}
