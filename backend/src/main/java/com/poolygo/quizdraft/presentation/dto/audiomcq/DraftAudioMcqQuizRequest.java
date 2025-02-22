package com.poolygo.quizdraft.presentation.dto.audiomcq;

import com.poolygo.quizdraft.presentation.dto.DraftRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DraftAudioMcqQuizRequest implements DraftRequest {

    private String title;
    private String thumbnailUrl;
    private String description;
    private String formerDraftId;
    private String type;

    private List<DraftAudioMcqQuestionRequest> questions;
}
