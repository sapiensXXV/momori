package com.poolygo.quiz.presentation.dto.request.quiz;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public class QuizListRequest {
    private QuizSearchType searchType;

    public enum QuizSearchType {
        LATEST,
        POPULAR;

        public QuizSearchType from(String type) {
            return Arrays.stream(QuizListRequest.QuizSearchType.values())
                .filter(qst -> qst.name().equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 퀴즈 타입입니다. type=" + type.toUpperCase()));
        }
    }
}
