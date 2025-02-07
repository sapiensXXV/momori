package com.poolygo.quiz.application.strategy;

import com.poolygo.quiz.domain.Quiz;
import com.poolygo.quiz.presentation.dto.response.detail.QuizDetailResponse;

public interface QuizMappingStrategy {
    QuizDetailResponse map(Quiz quiz);
}
