package com.poolygo.quizdraft.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AudioMcqQuestionDraft {
    private String audioId;
    private int startTime;
    private int playDuration;
}
