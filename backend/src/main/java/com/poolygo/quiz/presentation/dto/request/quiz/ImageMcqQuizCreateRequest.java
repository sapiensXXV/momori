package com.poolygo.quiz.presentation.dto.request.quiz;

import java.util.List;

public class ImageMcqQuizCreateRequest {

    private String title;
    private String thumbnailUrl;
    private String description;
    private String type;
    private List<ImageMcqQuizCreateRequest> questions;

}
