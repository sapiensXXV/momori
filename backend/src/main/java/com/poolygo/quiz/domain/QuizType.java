package com.poolygo.quiz.domain;

import com.poolygo.global.exception.ExceptionCode;
import com.poolygo.global.exception.QuizException;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public enum QuizType {
    BINARY_CHOICE,
    IMAGE_MCQ,
    AUDIO_MCQ,
    IMAGE_SUBJECTIVE,
    AUDIO_SUBJECTIVE;

    public static QuizType from(String find) {
        return Arrays.stream(QuizType.values())
            .filter(type -> type.name().equalsIgnoreCase(find))
            .findFirst()
            .orElseThrow(() -> new QuizException(ExceptionCode.QUIZ_TYPE_NOT_FOUND));
    }
}
