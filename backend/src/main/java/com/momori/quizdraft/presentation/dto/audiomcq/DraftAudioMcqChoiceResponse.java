package com.momori.quizdraft.presentation.dto.audiomcq;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DraftAudioMcqChoiceResponse {

    private final String content;
    private final boolean answer;

}
