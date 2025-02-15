package com.poolygo.quiz.presentation.dto.request.question;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AudioMcqQuestionCreateRequest {
    private String audioUrl;
    private List<AudioMcqChoiceCreateRequest> choices;
}
