package com.poolygo.quiz.presentation.dto.result;

import lombok.Getter;

import java.util.List;

@Getter
public class AudioSubQuizResultRequest extends QuizResultRequest {

    private final List<AudioSubQuestionResultRequest> questions;

    public AudioSubQuizResultRequest(String quizId, String type, int score, List<AudioSubQuestionResultRequest> questions) {
        super(quizId, type, score);
        this.questions = questions;
    }

    @Getter
    public static class AudioSubQuestionResultRequest extends QuestionResultRequest {
        // TODO: 추후 필요한 필드를 다시 정의하고 생성자도 다시 만들기

        public AudioSubQuestionResultRequest(String questionId, boolean isCorrect) {
            super(questionId, isCorrect);
        }

    }
}
