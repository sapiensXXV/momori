package com.momori.quizdraft.domain;

import lombok.Getter;

import java.util.List;


@Getter
public class ImageSubQuestionDraft extends QuestionDraft{

    private final String imageUrl;
    private final List<String> answers;

    private ImageSubQuestionDraft(String imageUrl, List<String> answers) {
        this.imageUrl = imageUrl;
        this.answers = answers;
    }

    public static ImageSubQuestionDraft of(String imageUrl, List<String> answers) {
        return new ImageSubQuestionDraft(imageUrl, answers);
    }
}
