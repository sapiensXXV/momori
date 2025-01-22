package com.poolygo.quiz.presentation.dto.request.quiz;

import com.poolygo.quiz.presentation.dto.request.question.AudioSubjectiveQuestionCreateRequest;

import java.util.List;

public class AudioSubjectiveQuizCreateRequest {
    List<AudioSubjectiveQuestionCreateRequest> questions;
}
