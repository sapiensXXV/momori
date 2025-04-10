package com.momori.quiz.application.strategy;

import com.momori.quiz.domain.AudioSubQuestion;
import com.momori.quiz.domain.Quiz;
import com.momori.quiz.domain.mapper.AudioSubMapper;
import com.momori.quiz.presentation.dto.response.detail.AudioSubQuestionDetailResponse;
import com.momori.quiz.presentation.dto.response.detail.QuizDetailResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AudioSubQuizMappingStrategy implements QuizMappingStrategy {

    private final AudioSubMapper audioSubMapper;

    public AudioSubQuizMappingStrategy(AudioSubMapper audioSubMapper) {
        this.audioSubMapper = audioSubMapper;
    }

    @Override
    public QuizDetailResponse map(Quiz quiz) {
        List<AudioSubQuestionDetailResponse> questions = quiz.getQuestions().stream()
            .map(q -> {
                AudioSubQuestion question = (AudioSubQuestion) q;
                return audioSubMapper.toAudioSubQuestionDetailResponse(question);
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
