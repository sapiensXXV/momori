package com.momori.quizdraft.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AudioMcqQuestionDraft extends QuestionDraft{
    private String audioId;
    private int startTime;
    private int playDuration;

    private List<AudioMcqChoiceDraft> choices;
}
