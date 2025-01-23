package com.poolygo.quiz.domain.factory;


import com.poolygo.quiz.domain.ImageMcqQuestion;
import com.poolygo.quiz.domain.Quiz;
import com.poolygo.quiz.domain.QuizType;
import com.poolygo.quiz.presentation.dto.request.question.ImageMcqQuestionCreateRequest;
import com.poolygo.quiz.presentation.dto.request.quiz.ImageMcqQuizCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuizFactory {

    private final QuestionFactory questionFactory;

    public Quiz from(ImageMcqQuizCreateRequest request) {
        String title = request.getTitle();
        String thumbnailUrl = request.getThumbnailUrl();
        String description = request.getDescription();
        QuizType type = QuizType.from(request.getType());

        List<ImageMcqQuestionCreateRequest> questionRequests = request.getQuestions();
        List<ImageMcqQuestion> questions = questionRequests.stream()
            .map(questionFactory::from)
            .toList();

        // TODO: 유저 정보 필요
        return Quiz.builder()
            .title(title)
            .description(description)
            .thumbnailUrl(thumbnailUrl)
            .type(type)
            .views(0)
            .tries(0)
            .likes(0)
            .questions(questions)
            .build();
    }

}
