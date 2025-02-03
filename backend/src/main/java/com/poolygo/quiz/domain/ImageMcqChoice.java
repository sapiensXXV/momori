package com.poolygo.quiz.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ImageMcqChoice extends McqChoice{
    @JsonProperty("content")
    private final String content;

    @JsonProperty("isAnswer")
    private final boolean isAnswer;

    private ImageMcqChoice(String content, boolean isAnswer) {
        this.content = content;
        this.isAnswer = isAnswer;
    }

    public static ImageMcqChoice of(String content, boolean isAnswer) {
        return new ImageMcqChoice(content, isAnswer);
    }
}
