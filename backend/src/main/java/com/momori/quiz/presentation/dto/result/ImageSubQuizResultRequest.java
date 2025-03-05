package com.momori.quiz.presentation.dto.result;


import lombok.Getter;

import java.util.List;

@Getter
public class ImageSubQuizResultRequest extends QuizResultRequest{

    private final List<ImageSubQuestionResultRequest> questions;

    public ImageSubQuizResultRequest(
        final String quizId,
        final String type,
        final int score,
        final List<ImageSubQuestionResultRequest> questions
    ) {
        super(quizId, type, score);
        this.questions = questions;
    }

    @Getter
    public static class ImageSubQuestionResultRequest extends QuestionResultRequest {
        public ImageSubQuestionResultRequest(String questionId, boolean isCorrect) {
            super(questionId, isCorrect);
        }// 사용자가 한 입력은 받지 않음.
    }

}
