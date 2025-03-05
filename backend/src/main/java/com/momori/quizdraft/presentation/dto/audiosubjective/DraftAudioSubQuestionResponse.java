package com.momori.quizdraft.presentation.dto.audiosubjective;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DraftAudioSubQuestionResponse {

    private final String audioId;
    private final int startTime;
    private final int playDuration;
    private final List<String> answers;

}
