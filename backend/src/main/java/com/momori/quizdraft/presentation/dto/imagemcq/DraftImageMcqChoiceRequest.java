package com.momori.quizdraft.presentation.dto.imagemcq;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DraftImageMcqChoiceRequest {

    private String content;
    private boolean isAnswer;

}
