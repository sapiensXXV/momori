package com.poolygo.quizdraft.presentation.dto.audiomcq;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DraftAudioMcqQuestionRequest {

    private String audioId;
    private String startTime;
    private String playDuration;

    private List<DraftAudioMcqChoiceRequest> choices;
}
