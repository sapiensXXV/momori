package com.poolygo.quiz.presentation.dto.response.detail;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@AllArgsConstructor
@SuperBuilder
public class AudioSubQuestionDetailResponse extends QuestionDetailResponse {
    private String audioId;
    private int startTime;
    private int playDuration;
    private List<String> answers;
}
