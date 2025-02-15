package com.poolygo.quiz.presentation.dto.request.quiz;

import com.poolygo.quiz.presentation.dto.request.question.BinaryChoiceQuestionCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
public class BinaryChoiceQuizCreateRequest extends QuizCreateRequest {

    private List<BinaryChoiceQuestionCreateRequest> questions;
}
