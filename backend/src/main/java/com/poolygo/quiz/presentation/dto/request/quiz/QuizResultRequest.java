package com.poolygo.quiz.presentation.dto.request.quiz;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QuizResultRequest {

    private final String quizId;
    private final int score;
    private final List<QuestionResultRequest> questions;

    @Getter
    @AllArgsConstructor
    public static class QuestionResultRequest {
        private final String questionId;
        private final boolean isCorrect;
    }

}
