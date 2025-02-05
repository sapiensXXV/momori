package com.poolygo.quiz.presentation.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuizSummaryResponse {
    private String id;
    private String thumbnailUrl;
    private String title;
    private String description;
}
