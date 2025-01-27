package com.poolygo.quizdraft.domain;


import lombok.Getter;

import java.util.List;

@Getter
public class ImageMcqQuestionDraft extends QuestionDraft {

    private String imageUrl;
    private List<? extends ChoiceDraft> choices;

    private ImageMcqQuestionDraft(
        final String imageUrl,
        final List<? extends ChoiceDraft> choices
    ) {
        this.imageUrl = imageUrl;
        this.choices = choices;
    }

    public static ImageMcqQuestionDraft of(
        final String imageUrl,
        final List<? extends ChoiceDraft> choices
    ) {
        return new ImageMcqQuestionDraft(imageUrl, choices);
    }
}
