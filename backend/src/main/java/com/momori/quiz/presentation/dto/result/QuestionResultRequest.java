package com.momori.quiz.presentation.dto.result;

import lombok.Getter;

@Getter
public abstract class QuestionResultRequest {

    private final String questionId;
    private final boolean isCorrect;

    public QuestionResultRequest(String questionId, boolean isCorrect) {
        this.questionId = questionId;
        this.isCorrect = isCorrect;

    }
}
