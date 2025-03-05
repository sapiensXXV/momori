package com.momori.quizdraft.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AudioSubQuestionDraft extends QuestionDraft {
    private String audioId;
    private int startTime;
    private int playDuration;

    private List<String> answers;
}
