package com.momori.quizdraft.presentation.dto.audiosubjective;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DraftAudioSubQuestionRequest {
    private String audioId;
    private int startTime;
    private int playDuration;
    private List<String> answers;
}
