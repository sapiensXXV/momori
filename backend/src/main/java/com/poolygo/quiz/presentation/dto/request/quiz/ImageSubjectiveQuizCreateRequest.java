package com.poolygo.quiz.presentation.dto.request.quiz;

import com.poolygo.quiz.presentation.dto.request.question.ImageSubjectiveQuestionCreateRequest;

import java.util.List;

public class ImageSubjectiveQuizCreateRequest {

    private String title;
    private String thumbnailUrl;
    private String description;
    private String type;
    private List<ImageSubjectiveQuestionCreateRequest> questions;

}
