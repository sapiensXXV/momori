package com.momori.global.exception;

public class QuizException extends RuntimeException {

    private final int code;
    private final String message;

    public QuizException(ExceptionCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }
}
