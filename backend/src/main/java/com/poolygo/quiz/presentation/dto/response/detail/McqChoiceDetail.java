package com.poolygo.quiz.presentation.dto.response.detail;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class McqChoiceDetail {

    @JsonProperty("content")
    private String content;

    @JsonProperty("isAnswer")
    private boolean answer;

    @JsonProperty("selectedCount")
    private int selectedCount;
}