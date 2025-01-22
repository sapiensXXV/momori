package com.poolygo.global.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    INVALID_QUIZ_TYPE(2001, "존재하지 않는 퀴즈 타입입니다.");

    private int code;
    private String message;

    ExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
