package com.momori.quiz.presentation.dto.response.detail;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AudioMcqQuestionDetailResponse extends QuestionDetailResponse {
    private String audioId;
    private int startTime;
    private int playDuration;
    private List<McqChoiceDetail> choices;
}
