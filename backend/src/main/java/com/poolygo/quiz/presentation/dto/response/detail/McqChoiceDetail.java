package com.poolygo.quiz.presentation.dto.response.detail;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class McqChoiceDetail {

    @JsonProperty("content")
    private final String content;
    @JsonProperty("isAnswer")
    private final boolean isAnswer;

    private McqChoiceDetail(String content, boolean isAnswer) {
        this.content = content;
        this.isAnswer = isAnswer;
    }

    public static McqChoiceDetail of(String content, boolean isAnswer) {
        return new McqChoiceDetail(content, isAnswer);
    }

}
