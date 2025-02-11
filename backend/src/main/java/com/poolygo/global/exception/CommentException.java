package com.poolygo.global.exception;

public class CommentException extends RuntimeException {
    private final int code;
    private final String message;

    public CommentException(final ExceptionCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }
}
