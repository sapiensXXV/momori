package com.poolygo.quiz.presentation.dto.request.quiz;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QuizResultRequest {


    private final String quizId;
    private final String type; // 퀴즈 타입
    private final int score;
    private final List<QuestionResultRequest> questions;

    @Getter
    @AllArgsConstructor
    public static class QuestionResultRequest {
        private final String questionId;
        private final boolean isCorrect;
        private final List<Integer> choices; // 문제에서 선택한 선택지 번호. 객관식 문제에서만 전달되는 데이터이다.
    }

}
