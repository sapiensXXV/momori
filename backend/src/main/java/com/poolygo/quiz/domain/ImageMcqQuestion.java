package com.poolygo.quiz.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ImageMcqQuestion extends Question {
    private String imageUrl;
    private List<ImageMcqChoice> choices;

    public ImageMcqQuestion(
        final String imageUrl,
        final List<ImageMcqChoice> choices
    ) {
        this.imageUrl = imageUrl;
        this.choices = choices;
    }
}
