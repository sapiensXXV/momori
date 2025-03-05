package com.momori.quiz.presentation.dto.result;

import lombok.Getter;

import java.util.List;


@Getter
public class AudioMcqQuizResultRequest extends QuizResultRequest {

    private final List<AudioMcqQuestionResultRequest> questions;

    public AudioMcqQuizResultRequest(String quizId, String type, int score, List<AudioMcqQuestionResultRequest> questions) {
        super(quizId, type, score);
        this.questions = questions;
    }

    @Getter
    public static class AudioMcqQuestionResultRequest extends QuestionResultRequest {
        private final List<Integer> choices;

        public AudioMcqQuestionResultRequest(
            final String questionId,
            final boolean isCorrect,
            final List<Integer> choices
        ) {
            super(questionId, isCorrect);
            this.choices = choices;
        }
    }
}
