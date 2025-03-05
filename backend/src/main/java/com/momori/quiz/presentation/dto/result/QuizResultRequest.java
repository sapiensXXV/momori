package com.momori.quiz.presentation.dto.result;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class QuizResultRequest {
    private final String quizId;
    private final String type; // 퀴즈 타입
    private final int score;
}
