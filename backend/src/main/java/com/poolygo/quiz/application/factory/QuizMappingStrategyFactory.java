package com.poolygo.quiz.application.factory;

import com.poolygo.quiz.application.strategy.ImageMcqQuizMappingStrategy;
import com.poolygo.quiz.application.strategy.ImageSubQuizMappingStrategy;
import com.poolygo.quiz.application.strategy.QuizMappingStrategy;
import com.poolygo.quiz.domain.QuizType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.poolygo.quiz.domain.QuizType.IMAGE_MCQ;
import static com.poolygo.quiz.domain.QuizType.IMAGE_SUBJECTIVE;


@Service
public class QuizMappingStrategyFactory {

    private final Map<QuizType, QuizMappingStrategy> strategyMap;

    public QuizMappingStrategyFactory(
        // 전략 클래스 주입
        final ImageMcqQuizMappingStrategy imageMcqQuizMappingStrategy,
        final ImageSubQuizMappingStrategy imageSubQuizMappingStrategy
    ) {
        this.strategyMap = new HashMap<>();
        strategyMap.put(IMAGE_MCQ, imageMcqQuizMappingStrategy);
        strategyMap.put(IMAGE_SUBJECTIVE, imageSubQuizMappingStrategy);
    }

    public QuizMappingStrategy getStrategy(final QuizType quizType) {
        QuizMappingStrategy strategy = strategyMap.get(quizType);
        if (strategy == null) {
            throw new IllegalArgumentException("매핑 전략을 찾을 수 없는 QuizType=" + quizType);
        }

        return strategy;
    }
}
