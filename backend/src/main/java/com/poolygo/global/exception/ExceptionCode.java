package com.poolygo.global.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    INVALID_QUIZ_TYPE(2001, "존재하지 않는 퀴즈 타입입니다."),
    INVALID_QUIZ_ID(2002, "잘못된 퀴즈 ID 입니다."),

    INVALID_DRAFT_ID(3001, "존재하지 않는 임시 저장 ID 입니다."),

    INVALID_TOKEN(4001, "올바르지 않은 JWT 토큰입니다."),
    TOKEN_AUTHENTICATION_FAIL(4002, "토큰으로부터 사용자 정보를 추출하는데 실패하였습니다."),

    INVALID_S3_OBJECT_KEY(5001, "잘못된 객체 URL 입니다."),

    INVALID_COMMENT_FORMAT(6001, "댓글 요청 형식이 잘못되었습니다.");

    private int code;
    private String message;

    ExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
