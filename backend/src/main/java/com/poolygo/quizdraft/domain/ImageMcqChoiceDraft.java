package com.poolygo.quizdraft.domain;


import lombok.Getter;

@Getter
public class ImageMcqChoiceDraft extends McqChoiceDraft {

    private String content;
    private boolean isAnswer;

    private ImageMcqChoiceDraft(
        final String content,
        final boolean isAnswer
    ) {
        this.content = content;
        this.isAnswer = isAnswer;
    }

    public static ImageMcqChoiceDraft of(String content, boolean isAnswer) {
        return new ImageMcqChoiceDraft(content, isAnswer);
    }
}
