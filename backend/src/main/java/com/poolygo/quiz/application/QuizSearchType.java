package com.poolygo.quiz.application;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;


@Slf4j
public enum QuizSearchType {
    POPULAR,
    LATEST;

    public static QuizSearchType from(String type) {
        log.info("type={}", type);
        if (type == null || type.equalsIgnoreCase("null")) return null;
        return Arrays.stream(QuizSearchType.values())
            .filter(v -> v.name().equalsIgnoreCase(type))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 검색 타입입니다."));
    }

}
