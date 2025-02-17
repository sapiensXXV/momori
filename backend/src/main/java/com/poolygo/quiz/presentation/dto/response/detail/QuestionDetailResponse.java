package com.poolygo.quiz.presentation.dto.response.detail;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public abstract class QuestionDetailResponse {
    private String questionId;
    private int tryCount;
    private int correctCount;
}
