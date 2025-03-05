package com.momori.quizdraft.domain;


import lombok.Getter;

@Getter
public class ImageMcqChoiceDraft extends McqChoiceDraft {

    private String content;
    private boolean answer;

    private ImageMcqChoiceDraft(
        final String content,
        final boolean answer
    ) {
        this.content = content;
        this.answer = answer;
    }

    public static ImageMcqChoiceDraft of(String content, boolean isAnswer) {
        return new ImageMcqChoiceDraft(content, isAnswer);
    }
}
