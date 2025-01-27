package com.poolygo.quiz.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ImageMcqChoice {
    private String content;
    private boolean isAnswer;
}
