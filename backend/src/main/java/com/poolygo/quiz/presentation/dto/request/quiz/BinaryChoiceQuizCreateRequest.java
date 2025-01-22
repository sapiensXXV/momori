package com.poolygo.quiz.presentation.dto.request.quiz;

import com.poolygo.quiz.presentation.dto.request.question.BinaryChoiceQuestionCreateRequest;

import java.util.List;

public class BinaryChoiceQuizCreateRequest {

    private String title;
    private String thumbnailUrl;
    private String description;
    private String type;
    private List<BinaryChoiceQuestionCreateRequest> questions;
}
