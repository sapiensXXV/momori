package com.momori.quiz.presentation.dto.request.question;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AudioSubQuestionCreateRequest {

    private String audioId;
    private int startTime;
    private int playDuration;
    private List<String> answers;
}
