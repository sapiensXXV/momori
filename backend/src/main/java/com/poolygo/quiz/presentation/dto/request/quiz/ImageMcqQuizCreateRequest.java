package com.poolygo.quiz.presentation.dto.request.quiz;

import com.poolygo.quiz.presentation.dto.request.question.ImageMcqQuestionCreateRequest;
import lombok.Getter;

import java.util.List;


@Getter
public class ImageMcqQuizCreateRequest extends QuizCreateRequest {

    private List<ImageMcqQuestionCreateRequest> questions;

    public ImageMcqQuizCreateRequest(
        final String title,
        final String draftId,
        final String thumbnailUrl,
        final String description,
        final String type,
        final List<ImageMcqQuestionCreateRequest> questions
    ) {
        super(title, draftId, thumbnailUrl, description, type);
        this.questions = questions;
    }
}
