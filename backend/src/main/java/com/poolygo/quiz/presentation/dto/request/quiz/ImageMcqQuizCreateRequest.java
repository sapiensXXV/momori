package com.poolygo.quiz.presentation.dto.request.quiz;

import com.poolygo.quiz.presentation.dto.request.question.ImageMcqQuestionCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
public class ImageMcqQuizCreateRequest {

    private String title;
    private String thumbnailUrl;
    private String description;
    private String type;
    private List<ImageMcqQuestionCreateRequest> questions;

}
