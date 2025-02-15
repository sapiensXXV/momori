package com.poolygo.quiz.presentation.dto.request.quiz;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class QuizCreateRequest {
    private String title;
    private String draftId;
    private String thumbnailUrl;
    private String description;
    private String type;
}
