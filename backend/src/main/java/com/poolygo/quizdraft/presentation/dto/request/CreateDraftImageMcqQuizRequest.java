package com.poolygo.quizdraft.presentation.dto.request;

import com.poolygo.quizdraft.presentation.dto.CreateDraftRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class CreateDraftImageMcqQuizRequest implements CreateDraftRequest {

    private String title;
    private String thumbnailUrl;
    private String description;
    private String formerDraftId;
    private String type;

    private List<DraftImageMcqQuestionRequest> questions;

}
