package com.momori.global.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    QUIZ_TYPE_NOT_FOUND(2001, "존재하지 않는 퀴즈 타입입니다."),
    INVALID_QUIZ_TYPE(2002, "잘못된 퀴즈 타입입니다."),
    INVALID_QUIZ_ID(2003, "잘못된 퀴즈 ID 입니다."),
    INVALID_QUESTION_ID(2004, "잘못된 문제 ID 입니다."),
    QUIZ_CREATE_FAIL(2005, "퀴즈를 생성하는데 실패했습니다."),
    QUIZ_DB_MAPPING_FAIL(2006, "퀴즈를 로드하는데 실패했습니다."),

    INVALID_DRAFT_ID(3001, "존재하지 않는 임시 저장 ID 입니다."),
    DRAFT_SAVE_FAIL(3002, "퀴즈를 임시저장하는데 실패했습니다."),

    INVALID_TOKEN(4001, "올바르지 않은 JWT 토큰입니다."),
    INVALID_USER_ID(4002, "사용자를 식별하는데 실패하였습니다. 존재하지 않는 id 입니다."),
    TOKEN_NOT_FOUND(4003, "인증 토큰이 없습니다."),
    TOKEN_AUTHENTICATION_FAIL(4004, "토큰으로부터 사용자 정보를 추출하는데 실패하였습니다."),
    TOKEN_EXPIRED(4005, "토큰의 만료 시간이 지났습니다."),


    INVALID_S3_OBJECT_KEY(5001, "잘못된 객체 URL 입니다."),

    // 댓글 에러
    INVALID_COMMENT_FORMAT(6001, "댓글 요청 형식이 잘못되었습니다."),
    INVALID_COMMENT_ID(6002, "댓글을 찾을 수 없습니다."),
    INVALID_COMMENT_PASSWORD(6003, "댓글 비밀번호가 일치하지 않습니다."),
    INVALID_COMMENT_USER(6004,"댓글을 작성한 사용자만 삭제할 수 있습니다."),
    INVALID_COMMENT_TYPE(6005,"잘못된 댓글 타입 형식입니다.");


    private int code;
    private String message;

    ExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
