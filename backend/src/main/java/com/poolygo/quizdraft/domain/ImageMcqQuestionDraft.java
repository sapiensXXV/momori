package com.poolygo.quizdraft.domain;


import lombok.Getter;

import java.util.List;

@Getter
public class ImageMcqQuestionDraft extends QuestionDraft {

    private String imageUrl;
    private List<? extends McqChoiceDraft> choices;

    private ImageMcqQuestionDraft(
        final String imageUrl,
        final List<? extends McqChoiceDraft> choices
    ) {
        this.imageUrl = imageUrl;
        this.choices = choices;
    }

    public static ImageMcqQuestionDraft of(
        final String imageUrl,
        final List<? extends McqChoiceDraft> choices
    ) {
        return new ImageMcqQuestionDraft(imageUrl, choices);
    }
}
