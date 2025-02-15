package com.poolygo.quiz.presentation.dto.request.quiz;

import com.poolygo.quiz.presentation.dto.request.question.AudioMcqQuestionCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
public class AudioMcqQuizCreateRequest extends QuizCreateRequest {
    private List<AudioMcqQuestionCreateRequest> questions;
}
