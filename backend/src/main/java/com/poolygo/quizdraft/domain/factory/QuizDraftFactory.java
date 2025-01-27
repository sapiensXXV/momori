package com.poolygo.quizdraft.domain.factory;

import com.poolygo.quiz.domain.QuizType;
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

    public QuizDraft from(DraftImageMcqQuizRequest request) {

        List<QuestionDraft> questions = request.getQuestions().stream()
            .map(questionDraftFactory::from)
            .toList();

        return QuizDraft.builder()
            .title(request.getTitle())
            .description(request.getDescription())
            .type(QuizType.from(request.getType()))
            .questions(questions)
            .build();
    }

}
