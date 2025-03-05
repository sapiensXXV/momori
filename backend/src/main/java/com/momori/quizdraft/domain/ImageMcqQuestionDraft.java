package com.momori.quizdraft.domain;


import lombok.Getter;

import java.util.List;

@Getter
public class ImageMcqQuestionDraft extends QuestionDraft {

    private final String imageUrl;
    private final List<ImageMcqChoiceDraft> choices;

    private ImageMcqQuestionDraft(
        final String imageUrl,
        final List<ImageMcqChoiceDraft> choices
    ) {
        this.imageUrl = imageUrl;
        this.choices = choices;
    }

    public static ImageMcqQuestionDraft of(
        final String imageUrl,
        final List<ImageMcqChoiceDraft> choices
    ) {
        return new ImageMcqQuestionDraft(imageUrl, choices);
    }
}
