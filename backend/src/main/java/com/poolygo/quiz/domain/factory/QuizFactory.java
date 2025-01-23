package com.poolygo.quiz.domain.factory;


import com.poolygo.auth.dto.UserAuthInfo;
import com.poolygo.quiz.domain.ImageMcqQuestion;
import com.poolygo.quiz.domain.Quiz;
import com.poolygo.quiz.domain.QuizType;
import com.poolygo.quiz.domain.UserInfo;
import com.poolygo.quiz.presentation.dto.request.question.ImageMcqQuestionCreateRequest;
import com.poolygo.quiz.presentation.dto.request.quiz.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuizFactory {

    private final QuestionFactory questionFactory;
    private final UserInfoFactory userInfoFactory;

    public Quiz from(ImageMcqQuizCreateRequest request, UserAuthInfo auth) {
        String title = request.getTitle();
        String thumbnailUrl = request.getThumbnailUrl();
        String description = request.getDescription();
        QuizType type = QuizType.from(request.getType());

        List<ImageMcqQuestionCreateRequest> questionRequests = request.getQuestions();
        List<ImageMcqQuestion> questions = questionRequests.stream()
            .map(questionFactory::from)
            .toList();

        UserInfo userInfo = userInfoFactory.from(auth);

        // TODO: 유저 정보 필요
        return Quiz.builder()
            .userInfo(userInfo)
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


    public Quiz from(ImageSubjectiveQuizCreateRequest request, UserAuthInfo auth) {
        return null;
    }

    public Quiz from(AudioMcqQuizCreateRequest request, UserAuthInfo auth) {
        return null;
    }

    public Quiz from(AudioSubjectiveQuizCreateRequest request, UserAuthInfo auth) {
        return null;
    }

    public Quiz from(BinaryChoiceQuizCreateRequest request, UserAuthInfo auth) {
        return null;
    }
}
