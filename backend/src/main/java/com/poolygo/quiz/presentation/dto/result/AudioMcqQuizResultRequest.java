package com.poolygo.quiz.presentation.dto.result;

import lombok.Getter;

import java.util.List;


@Getter
public class AudioMcqQuizResultRequest extends QuizResultRequest {

    private final List<AudioMcqQuestionResult> questions;

    public AudioMcqQuizResultRequest(String quizId, String type, int score, List<AudioMcqQuestionResult> questions) {
        super(quizId, type, score);
        this.questions = questions;
    }

    @Getter
    public static class AudioMcqQuestionResult extends QuestionResultRequest {

        // TODO: 추후 필요한 필드를 다시 정의하고 생성자도 다시 만들기

        public AudioMcqQuestionResult(String questionId, boolean isCorrect) {
            super(questionId, isCorrect);
        }
    }
}
