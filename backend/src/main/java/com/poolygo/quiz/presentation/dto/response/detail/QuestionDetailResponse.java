package com.poolygo.quiz.presentation.dto.response.detail;

import lombok.Data;

@Data
public abstract class QuestionDetailResponse {
    private String questionId;
    private int tryCount;
    private int correctCount;
}
