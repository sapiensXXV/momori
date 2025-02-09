package com.poolygo.quiz.application.strategy;

import com.poolygo.quiz.domain.ImageMcqQuestion;
import com.poolygo.quiz.domain.Quiz;
import com.poolygo.quiz.domain.mapper.ImageMcqMapper;
import com.poolygo.quiz.presentation.dto.response.detail.ImageMcqQuestionDetailResponse;
import com.poolygo.quiz.presentation.dto.response.detail.QuizDetailResponse;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ImageMcqQuizMappingStrategy implements QuizMappingStrategy {

    private final ImageMcqMapper imageMcqMapper;

    public ImageMcqQuizMappingStrategy(ImageMcqMapper imageMcqMapper) {
        this.imageMcqMapper = imageMcqMapper;
    }

    @Override
    public QuizDetailResponse map(Quiz quiz) {
        // Quiz 내부의 type 이 IMAGE_MCQ 임을 가정
        List<ImageMcqQuestionDetailResponse> questions = quiz.getQuestions().stream()
            .map(q -> {
                ImageMcqQuestion question = (ImageMcqQuestion) q;
                return imageMcqMapper.toImageMcqQuestionDetailResponse(question);
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
