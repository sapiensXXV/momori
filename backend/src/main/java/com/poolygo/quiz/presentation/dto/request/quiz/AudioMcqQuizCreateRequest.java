package com.poolygo.quiz.presentation.dto.request.quiz;

import com.poolygo.quiz.presentation.dto.request.question.AudioMcqQuestionCreateRequest;
import lombok.Getter;

import java.util.List;


@Getter
public class AudioMcqQuizCreateRequest extends QuizCreateRequest {
    private List<AudioMcqQuestionCreateRequest> questions;

    public AudioMcqQuizCreateRequest(
        final String title,
        final String draftId,
        final String thumbnailUrl,
        final String description,
        final String type,
        final List<AudioMcqQuestionCreateRequest> questions
    ) {
        super(title, draftId, thumbnailUrl, description, type);
        this.questions = questions;
    }
}
