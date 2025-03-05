package com.momori.quiz.application.strategy;

import com.momori.quiz.domain.ImageSubQuestion;
import com.momori.quiz.domain.Quiz;
import com.momori.quiz.domain.mapper.ImageSubMapper;
import com.momori.quiz.presentation.dto.response.detail.ImageSubQuestionDetailResponse;
import com.momori.quiz.presentation.dto.response.detail.QuizDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ImageSubQuizMappingStrategy implements QuizMappingStrategy {

    private final ImageSubMapper imageSubMapper;

    @Override
    public QuizDetailResponse map(Quiz quiz) {
        List<ImageSubQuestionDetailResponse> questions = quiz.getQuestions().stream()
            .map(q -> {
                ImageSubQuestion question = (ImageSubQuestion) q;
                return imageSubMapper.toImageSubQuestionDetailResponse(question);
            })
            .toList();

        return QuizDetailResponse.builder()
            .id(quiz.getId())
            .title(quiz.getTitle())
            .thumbnailUrl(quiz.getThumbnailUrl())
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
