package com.poolygo.quizdraft.presentation.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DraftImageMcqChoiceRequest {

    private String content;
    private boolean isAnswer;

}
