package com.momori.quiz.application.strategy;

import com.momori.global.exception.ExceptionCode;
import com.momori.global.exception.QuizException;
import com.momori.quiz.domain.AudioMcqQuestion;
import com.momori.quiz.domain.Quiz;
import com.momori.quiz.domain.QuizType;
import com.momori.quiz.domain.mapper.AudioMcqMapper;
import com.momori.quiz.presentation.dto.response.detail.AudioMcqQuestionDetailResponse;
import com.momori.quiz.presentation.dto.response.detail.QuizDetailResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AudioMcqQuizMappingStrategy implements QuizMappingStrategy {

    private final AudioMcqMapper audioMcqMapper;

    public AudioMcqQuizMappingStrategy(AudioMcqMapper audioMcqMapper) {
        this.audioMcqMapper = audioMcqMapper;
    }

    @Override
    public QuizDetailResponse map(Quiz quiz) {
        // Quiz의 타입이 AUDIO_MCQ 임을 가정
        if (quiz.getType() != QuizType.AUDIO_MCQ) {
            throw new QuizException(ExceptionCode.QUIZ_TYPE_NOT_FOUND);
        }

        List<AudioMcqQuestionDetailResponse> questions = quiz.getQuestions().stream()
            .map(q -> {
                AudioMcqQuestion question = (AudioMcqQuestion) q;
                return audioMcqMapper.toImageMcqQuestionDetailResponse(question);
            })
            .toList();

        return QuizDetailResponse.builder()
            .id(quiz.getId())
            .thumbnailUrl(quiz.getThumbnailUrl())
            .title(quiz.getTitle())
            .description(quiz.getDescription())
            .type(quiz.getType().name())
            .questions(questions)
            .scoreDistribution(quiz.getScoreDistribution())
            .views(quiz.getViews())
            .tries(quiz.getTries())
            .likes(quiz.getLikes())
            .build();
    }
}
