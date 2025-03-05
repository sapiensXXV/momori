package com.momori.quiz.presentation.dto.request.quiz;

import com.momori.quiz.presentation.dto.request.question.ImageSubQuestionCreateRequest;
import lombok.Getter;

import java.util.List;


@Getter
public class ImageSubQuizCreateRequest extends QuizCreateRequest {

    private final List<ImageSubQuestionCreateRequest> questions;

    public ImageSubQuizCreateRequest(
        final String title,
        final String draftId,
        final String thumbnailUrl,
        final String description,
        final String type,
        final List<ImageSubQuestionCreateRequest> questions
    ) {
        super(title, draftId, thumbnailUrl, description, type);
        this.questions = questions;
    }
}
