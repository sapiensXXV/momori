package com.poolygo.quiz.application;

import java.util.Arrays;

public enum QuizSearchType {
    POPULAR,
    LATEST;

    public static QuizSearchType from(String type) {
        return Arrays.stream(QuizSearchType.values())
            .filter(v -> v.name().equalsIgnoreCase(type))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 검색 타입입니다."));
    }

}
