package com.poolygo.quiz.domain;

import com.poolygo.global.exception.ExceptionCode;
import com.poolygo.global.exception.QuizException;

import java.util.Arrays;

public enum QuizType {
    BINARY_CHOICE,
    IMAGE_MCQ,
    AUDIO_MCQ,
    IMAGE_SUBJECTIVE,
    AUDIO_SUBJECTIVE;

    public static QuizType from(String find) {
        return Arrays.stream(QuizType.values())
            .filter(type -> type.name().equals(find.toUpperCase()))
            .findFirst()
            .orElseThrow(() -> new QuizException(ExceptionCode.INVALID_QUIZ_TYPE));
    }
}
