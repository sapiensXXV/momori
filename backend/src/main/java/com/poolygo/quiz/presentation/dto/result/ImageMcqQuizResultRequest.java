package com.poolygo.quiz.presentation.dto.result;


import lombok.Getter;

import java.util.List;

@Getter
public class ImageMcqQuizResultRequest extends QuizResultRequest{

    private final List<ImageMcqQuestionResultRequest> questions;

    public ImageMcqQuizResultRequest(
        final String quizId,
        final String type,
        final int score,
        final List<ImageMcqQuestionResultRequest> questions
    ) {
        super(quizId, type, score);
        this.questions = questions;
    }

    @Getter
    public static class ImageMcqQuestionResultRequest extends QuestionResultRequest {
        private final List<Integer> choices; // 문제에서 선택한 선택지 번호. 객관식 문제에서만 전달되는 데이터이다.

        public ImageMcqQuestionResultRequest(
            final String questionId,
            final boolean isCorrect,
            final List<Integer> choices
        ) {
            super(questionId, isCorrect);
            this.choices = choices;
        }
    }

}
