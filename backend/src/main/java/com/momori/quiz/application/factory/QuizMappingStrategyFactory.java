package com.momori.quiz.application.factory;

import com.momori.quiz.application.strategy.*;
import com.momori.quiz.domain.QuizType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.momori.quiz.domain.QuizType.*;


@Service
public class QuizMappingStrategyFactory {

    private final Map<QuizType, QuizMappingStrategy> strategyMap;

    public QuizMappingStrategyFactory(
        // 전략 클래스 주입
        final ImageMcqQuizMappingStrategy imageMcqQuizMappingStrategy,
        final ImageSubQuizMappingStrategy imageSubQuizMappingStrategy,
        final AudioMcqQuizMappingStrategy audioMcqQuizMappingStrategy,
        final AudioSubQuizMappingStrategy audioSubQuizMappingStrategy
    ) {
        this.strategyMap = new HashMap<>();
        strategyMap.put(IMAGE_MCQ, imageMcqQuizMappingStrategy);
        strategyMap.put(IMAGE_SUBJECTIVE, imageSubQuizMappingStrategy);
        strategyMap.put(AUDIO_MCQ, audioMcqQuizMappingStrategy);
        strategyMap.put(AUDIO_SUBJECTIVE, audioSubQuizMappingStrategy);
    }

    public QuizMappingStrategy getStrategy(final QuizType quizType) {
        QuizMappingStrategy strategy = strategyMap.get(quizType);
        if (strategy == null) {
            throw new IllegalArgumentException("매핑 전략을 찾을 수 없는 QuizType=" + quizType);
        }

        return strategy;
    }
}
