package com.poolygo.quiz.presentation.dto.request.quiz;

import com.poolygo.quiz.presentation.dto.request.question.ImageSubQuestionCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
public class ImageSubQuizCreateRequest extends QuizCreateRequest {

    private List<ImageSubQuestionCreateRequest> questions;

}
