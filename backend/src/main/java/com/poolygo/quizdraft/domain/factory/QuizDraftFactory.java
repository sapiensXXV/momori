package com.poolygo.quizdraft.domain.factory;

import com.poolygo.quiz.domain.QuizType;
import com.poolygo.quiz.domain.factory.UserInfoFactory;
import com.poolygo.quizdraft.domain.QuestionDraft;
import com.poolygo.quizdraft.domain.QuizDraft;
import com.poolygo.quizdraft.presentation.dto.DraftImageMcqQuizRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class QuizDraftFactory {
    
    private final QuestionDraftFactory questionDraftFactory;
    private final UserInfoFactory userInfoFactory;

    public QuizDraft from(
        final DraftImageMcqQuizRequest request,
        final String userIdentifier,
        final String userProvider
    ) {

        List<QuestionDraft> questions = request.getQuestions().stream()
            .map(questionDraftFactory::from)
            .toList();

        return QuizDraft.builder()
            .title(request.getTitle())
            .description(request.getDescription())
            .userInfo(userInfoFactory.from(userIdentifier, userProvider))
            .type(QuizType.from(request.getType()))
            .questions(questions)
            .build();
    }

}
