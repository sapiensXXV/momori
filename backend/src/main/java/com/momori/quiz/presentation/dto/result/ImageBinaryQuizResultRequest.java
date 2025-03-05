package com.momori.quiz.presentation.dto.result;


import lombok.Getter;

import java.util.List;

@Getter
public class ImageBinaryQuizResultRequest extends QuizResultRequest {

    private final List<ImageBinaryQuestionResultRequest> questions;

    public ImageBinaryQuizResultRequest(String quizId, String type, int score, List<ImageBinaryQuestionResultRequest> questions) {
        super(quizId, type, score);
        this.questions = questions;
    }

    public static class ImageBinaryQuestionResultRequest extends QuestionResultRequest {
        // TODO: 추후 필요한 필드를 다시 정의하고 생성자도 다시 만들기
        public ImageBinaryQuestionResultRequest(String questionId, boolean isCorrect) {
            super(questionId, isCorrect);
        }
    }
}
