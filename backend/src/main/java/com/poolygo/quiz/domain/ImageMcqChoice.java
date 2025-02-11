package com.poolygo.quiz.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImageMcqChoice extends McqChoice {
    @JsonProperty("content")
    private final String content;

    @JsonProperty("isAnswer")
    private final boolean answer;

    @JsonProperty("selectedCount")
    private final int selectedCount;
}
