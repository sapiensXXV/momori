package com.momori.quiz.domain.factory;


import com.momori.auth.dto.UserAuthDto;
import com.momori.quiz.domain.*;
import com.momori.quiz.presentation.dto.request.quiz.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
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
            .scoreDistribution(new ArrayList<>(Collections.nCopies(10, 0)))
            .build();
    }


    public Quiz from(ImageSubQuizCreateRequest request, UserAuthDto auth) {
        List<ImageSubQuestion> questions = request.getQuestions().stream()
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
            .scoreDistribution(new ArrayList<>(Collections.nCopies(10, 0)))
            .build();
    }

    public Quiz from(AudioMcqQuizCreateRequest request, UserAuthDto auth) {
        List<AudioMcqQuestion> questions = request.getQuestions().stream()
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
            .scoreDistribution(new ArrayList<>(Collections.nCopies(10, 0)))
            .build();
    }

    public Quiz from(AudioSubQuizCreateRequest request, UserAuthDto auth) {
        List<AudioSubQuestion> questions = request.getQuestions().stream()
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
            .scoreDistribution(new ArrayList<>(Collections.nCopies(10, 0)))
            .build();
    }

    public Quiz from(BinaryChoiceQuizCreateRequest request, UserAuthDto auth) {
        List<BinaryChoiceQuestion> questions = request.getQuestions().stream()
            .map(questionFactory::from)
            .toList();

        return Quiz.builder()
            .title(request.getTitle())
            .description(request.getDescription())
            .thumbnailUrl(request.getThumbnailUrl())
            .type(QuizType.from(request.getType()))
            .views(0)
            .tries(0)
            .likes(0)
            .questions(questions)
            .scoreDistribution(new ArrayList<>(Collections.nCopies(10, 0)))
            .build();
    }

}
