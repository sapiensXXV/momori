package com.poolygo.quiz.presentation.dto.response;


import lombok.Getter;

@Getter
public class QuizCreateResponse {

    private static String CREATE_SUCCESS_MSG = "Quiz created successfully";

    private final String message;
    private final String quizId;
    private final String title;

    public QuizCreateResponse(
        final String quizId,
        final String title
    ) {
        this.message = CREATE_SUCCESS_MSG;
        this.quizId = quizId;
        this.title = title;
    }
}
