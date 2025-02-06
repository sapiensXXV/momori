package com.poolygo.quiz.presentation.dto.response.detail;


import lombok.Getter;

import java.util.List;

@Getter
public class ImageMcqQuestionDetailResponse extends QuestionDetailResponse {

    private final String imageUrl;
    private final List<? extends McqChoiceDetail> choices;

    private ImageMcqQuestionDetailResponse(String imageUrl, List<? extends McqChoiceDetail> choices) {
        this.imageUrl = imageUrl;
        this.choices = choices;
    }

    public static ImageMcqQuestionDetailResponse of(String imageUrl, List<? extends McqChoiceDetail> choices) {
        return new ImageMcqQuestionDetailResponse(imageUrl, choices);
    }


}
