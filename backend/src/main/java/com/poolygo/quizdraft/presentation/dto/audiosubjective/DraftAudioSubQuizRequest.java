package com.poolygo.quizdraft.presentation.dto.audiosubjective;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DraftAudioSubQuizRequest {
    private String title;
    private String thumbnailUrl;
    private String description;
    private String formerDraftId;
    private String type;

    private List<DraftAudioSubQuestionRequest> questions;
}
