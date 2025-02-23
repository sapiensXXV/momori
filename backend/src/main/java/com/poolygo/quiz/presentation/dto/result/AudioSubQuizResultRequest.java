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
        public AudioSubQuestionResultRequest(String questionId, boolean isCorrect) {
            super(questionId, isCorrect);
        }

    }
}
