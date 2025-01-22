package com.poolygo.quiz.presentation.dto.request.quiz;

import com.poolygo.quiz.presentation.dto.request.question.AudioMcqQuestionCreateRequest;

import java.util.List;

public class AudioMcqQuizCreateRequest {
    private String title;
    private String thumbnailUrl;
    private String description;
    private String type;
    private List<AudioMcqQuestionCreateRequest> questions;
}
