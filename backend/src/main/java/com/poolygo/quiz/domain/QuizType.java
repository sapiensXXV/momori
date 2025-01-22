package com.poolygo.quiz.domain;

import java.util.Arrays;

public enum QuizType {
    BINARY_CHOICE,
    IMAGE_MCQ,
    AUDIO_MCQ,
    IMAGE_SUBJECTIVE,
    AUDIO_SUBJECTIVE;

    public static QuizType from(String find) {
        Arrays.stream(QuizType.values())
            .filter(type -> type.name().equals(find.toUpperCase()))
            .findFirst()
            .orElseThrow()
    }
}
