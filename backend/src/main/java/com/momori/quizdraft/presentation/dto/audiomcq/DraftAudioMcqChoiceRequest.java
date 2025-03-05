package com.momori.quizdraft.presentation.dto.audiomcq;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DraftAudioMcqChoiceRequest {
    private String content;
    private boolean answer;
}
