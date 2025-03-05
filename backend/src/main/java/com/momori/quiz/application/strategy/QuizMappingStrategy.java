package com.momori.quiz.application.strategy;

import com.momori.quiz.domain.Quiz;
import com.momori.quiz.presentation.dto.response.detail.QuizDetailResponse;

public interface QuizMappingStrategy {
    QuizDetailResponse map(Quiz quiz);
}
