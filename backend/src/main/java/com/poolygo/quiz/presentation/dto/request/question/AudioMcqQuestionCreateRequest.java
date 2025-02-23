package com.poolygo.quiz.presentation.dto.request.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class AudioMcqQuestionCreateRequest {
    private String audioId;
    private int startTime;
    private int playDuration;
    private List<AudioMcqChoiceCreateRequest> choices;
}
