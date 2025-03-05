package com.momori.quiz.presentation.dto.request.quiz;

import com.momori.quiz.presentation.dto.request.question.BinaryChoiceQuestionCreateRequest;
import lombok.Getter;

import java.util.List;


@Getter
public class BinaryChoiceQuizCreateRequest extends QuizCreateRequest {

    private List<BinaryChoiceQuestionCreateRequest> questions;

    public BinaryChoiceQuizCreateRequest(
        final String title,
        final String draftId,
        final String thumbnailUrl,
        final String description,
        final String type,
        final List<BinaryChoiceQuestionCreateRequest> questions
    ) {
        super(title, draftId, thumbnailUrl, description, type);
        this.questions = questions;
    }
}
