package com.momori.comment.domain;

import com.momori.global.exception.CommentException;
import com.momori.global.exception.ExceptionCode;

import java.util.Arrays;

public enum CommentType {
    ANONYMOUS,
    USER,
    ADMIN;

    public static CommentType from(String type) {
        return Arrays.stream(CommentType.values())
            .filter(t -> type.equalsIgnoreCase(t.name()))
            .findFirst()
            .orElseThrow(() -> new CommentException(ExceptionCode.INVALID_COMMENT_TYPE));
    }
}
