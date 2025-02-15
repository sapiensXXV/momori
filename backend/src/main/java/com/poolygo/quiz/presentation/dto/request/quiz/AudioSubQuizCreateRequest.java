package com.poolygo.quiz.presentation.dto.request.quiz;

import com.poolygo.quiz.presentation.dto.request.question.AudioSubQuestionCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
public class AudioSubQuizCreateRequest extends QuizCreateRequest {
    List<AudioSubQuestionCreateRequest> questions;
}
