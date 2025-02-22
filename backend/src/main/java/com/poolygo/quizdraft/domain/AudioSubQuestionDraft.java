package com.poolygo.quizdraft.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AudioSubQuestionDraft {
    private String audioId;
    private int startTime;
    private int playDuration;
}
