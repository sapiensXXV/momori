package com.poolygo.quiz.domain;


import lombok.Getter;

@Getter
public class ImageMcqChoice extends McqChoice{
    private String content;
    private boolean isAnswer;

    private ImageMcqChoice(String content, boolean isAnswer) {
        this.content = content;
        this.isAnswer = isAnswer;
    }

    public static ImageMcqChoice of(String content, boolean isAnswer) {
        return new ImageMcqChoice(content, isAnswer);
    }
}
