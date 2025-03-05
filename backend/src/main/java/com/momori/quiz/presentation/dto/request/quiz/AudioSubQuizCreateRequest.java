package com.momori.quiz.presentation.dto.request.quiz;

import com.momori.quiz.presentation.dto.request.question.AudioSubQuestionCreateRequest;
import lombok.Getter;

import java.util.List;


@Getter
public class AudioSubQuizCreateRequest extends QuizCreateRequest {
    List<AudioSubQuestionCreateRequest> questions;

    public AudioSubQuizCreateRequest(
        final String title,
        final String draftId,
        final String thumbnailUrl,
        final String description,
        final String type,
        final List<AudioSubQuestionCreateRequest> questions
    ) {
        super(title, draftId, thumbnailUrl, description, type);
        this.questions = questions;
    }
}
