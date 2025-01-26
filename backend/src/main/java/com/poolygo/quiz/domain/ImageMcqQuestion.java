package com.poolygo.quiz.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ImageMcqQuestion extends Question {
    private String imageUrl;
    private List<Integer> answers;

    public ImageMcqQuestion(
        final String imageUrl,
        final List<Integer> answers
    ) {
        this.imageUrl = imageUrl;
        this.answers = answers;
    }
}
