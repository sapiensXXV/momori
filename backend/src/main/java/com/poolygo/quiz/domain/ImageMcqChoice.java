package com.poolygo.quiz.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageMcqChoice extends McqChoice {
    @JsonProperty("content")
    private String content;

    @JsonProperty("isAnswer")
    private boolean answer;

    @JsonProperty("selectedCount")
    private int selectedCount;
}
