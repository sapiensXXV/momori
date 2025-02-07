package com.poolygo.quiz.presentation.dto.response.detail;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class McqChoiceDetail {

    @JsonProperty("content")
    private String content;

    @JsonProperty("isAnswer")
    private boolean isAnswer;

    @JsonProperty("selectedCount")
    private int selectedCount;
}