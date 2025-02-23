package com.poolygo.quizdraft.presentation.dto.audiosubjective;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class DraftAudioSubDetailResponse {
    private final String draftId;
    private final String quizType;
    private final String thumbnailUrl;
    private final String title;
    private final String description;
    private final List<DraftAudioSubQuestionResponse> questions;
}
