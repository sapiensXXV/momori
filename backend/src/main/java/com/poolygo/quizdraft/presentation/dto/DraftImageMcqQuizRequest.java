package com.poolygo.quizdraft.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DraftImageMcqQuizRequest {

    private String title;
    private String description;
    private String formerDraftId;
    private String type;

    private List<DraftImageMcqQuestionRequest> questions;

}
