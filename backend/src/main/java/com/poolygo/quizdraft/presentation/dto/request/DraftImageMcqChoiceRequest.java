package com.poolygo.quizdraft.presentation.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DraftImageMcqChoiceRequest {

    private String content;
    private boolean isAnswer;

}
