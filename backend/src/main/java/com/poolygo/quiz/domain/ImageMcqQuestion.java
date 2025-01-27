package com.poolygo.quiz.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ImageMcqQuestion extends Question {
    private String imageUrl;
    private List<? extends McqChoice> choices;

    private ImageMcqQuestion(
        final String imageUrl,
        final List<? extends McqChoice> choices
    ) {
        this.imageUrl = imageUrl;
        this.choices = choices;
    }

    public static ImageMcqQuestion of(
        final String imageUrl,
        final List<? extends McqChoice> choices
    ) {
        return new ImageMcqQuestion(imageUrl, choices);
    }
}
