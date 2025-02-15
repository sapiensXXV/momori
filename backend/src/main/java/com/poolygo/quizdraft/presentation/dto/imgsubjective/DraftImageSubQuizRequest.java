package com.poolygo.quizdraft.presentation.dto.imgsubjective;


import com.poolygo.quizdraft.presentation.dto.DraftRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DraftImageSubQuizRequest implements DraftRequest {

    private String title;
    private String thumbnailUrl;
    private String description;
    private String formerDraftId;
    private String type;

    private List<DraftImageSubQuestionRequest> questions;

    @Getter
    @AllArgsConstructor
    public static class DraftImageSubQuestionRequest {
        private String imageUrl;
        private List<String> answers;
    }
}
