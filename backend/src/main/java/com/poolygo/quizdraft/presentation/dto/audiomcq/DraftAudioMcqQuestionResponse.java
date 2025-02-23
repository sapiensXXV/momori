package com.poolygo.quizdraft.presentation.dto.audiomcq;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DraftAudioMcqQuestionResponse {

    private final String audioId;
    private final int startTime;
    private final int playDuration;
    private final List<DraftAudioMcqChoiceResponse> choices;

}
