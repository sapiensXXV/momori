package com.poolygo.quiz.presentation.dto.response.detail;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class QuizDetailResponse {

    private final String title;
    private final String description;
    private final String type;
    private final String thumbnailUrl;
    private final int views;
    private final int tries;
    private final int likes;

    private final List<? extends QuestionDetailResponse> questions;

    @Builder
    public QuizDetailResponse(
        String title,
        String description,
        String type,
        String thumbnailUrl,
        List<? extends QuestionDetailResponse> questions,
        int views,
        int tries,
        int likes
    ) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.thumbnailUrl = thumbnailUrl;
        this.questions = questions;
        this.views = views;
        this.tries = tries;
        this.likes = likes;
    }

}
