package com.poolygo.quizdraft.presentation.dto.imgsubjective;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class DraftImageSubDetailResponse {

    private final String draftId;
    private final String quizType;
    private final String thumbnailUrl;
    private final String title;
    private final String description;
    private final List<DraftImageSubQuestionResponse> questions;

    @Getter
    @AllArgsConstructor
    public static class DraftImageSubQuestionResponse {
        private final String imageUrl;
        private final List<String> answers;
    }

}
