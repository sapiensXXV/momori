package com.poolygo.quiz.domain.factory;


import com.poolygo.auth.dto.UserAuthDto;
import com.poolygo.quiz.domain.ImageMcqQuestion;
import com.poolygo.quiz.domain.ImageSubjectiveQuestion;
import com.poolygo.quiz.domain.Quiz;
import com.poolygo.quiz.domain.QuizType;
import com.poolygo.quiz.presentation.dto.request.quiz.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuizFactory {

    private final QuestionFactory questionFactory;
    private final UserInfoFactory userInfoFactory;

    public Quiz from(ImageMcqQuizCreateRequest request, UserAuthDto auth) {

        List<ImageMcqQuestion> questions = request.getQuestions().stream()
            .map(questionFactory::from)
            .toList();

        return Quiz.builder()
            .userInfo(userInfoFactory.from(auth))
            .title(request.getTitle())
            .description(request.getDescription())
            .thumbnailUrl(request.getThumbnailUrl())
            .type(QuizType.from(request.getType()))
            .views(0)
            .tries(0)
            .likes(0)
            .questions(questions)
            .build();
    }


    public Quiz from(ImageSubjectiveQuizCreateRequest request, UserAuthDto auth) {
        List<ImageSubjectiveQuestion> questions = request.getQuestions().stream()
            .map(questionFactory::from)
            .toList();

        return Quiz.builder()
            .userInfo(userInfoFactory.from(auth))
            .title(request.getTitle())
            .description(request.getDescription())
            .thumbnailUrl(request.getThumbnailUrl())
            .type(QuizType.from(request.getType()))
            .views(0)
            .tries(0)
            .likes(0)
            .questions(questions)
            .build();
    }

    public Quiz from(AudioMcqQuizCreateRequest request, UserAuthDto auth) {
        return null;
    }

    public Quiz from(AudioSubjectiveQuizCreateRequest request, UserAuthDto auth) {
        return null;
    }

    public Quiz from(BinaryChoiceQuizCreateRequest request, UserAuthDto auth) {
        return null;
    }

}
