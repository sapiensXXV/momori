package com.momori.quizdraft.presentation.dto.audiomcq;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class DraftAudioMcqDetailResponse {

    private final String draftId;
    private final String quizType;
    private final String thumbnailUrl;
    private final String title;
    private final String description;
    private final List<DraftAudioMcqQuestionResponse> questions;

}
