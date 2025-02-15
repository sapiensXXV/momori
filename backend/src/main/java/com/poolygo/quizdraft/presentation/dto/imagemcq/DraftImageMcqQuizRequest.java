package com.poolygo.quizdraft.presentation.dto.imagemcq;

import com.poolygo.quizdraft.presentation.dto.DraftRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class DraftImageMcqQuizRequest implements DraftRequest {

    private String title;
    private String thumbnailUrl;
    private String description;
    private String formerDraftId;
    private String type;

    private List<DraftImageMcqQuestionRequest> questions;

}
