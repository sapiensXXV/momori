package com.poolygo.quiz.presentation.dto.request.question;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BinaryChoiceQuestionCreateRequest {

    private ChoiceItemRequest first;
    private ChoiceItemRequest second;

    @Getter
    @AllArgsConstructor
    public static class ChoiceItemRequest {
        private String imageUrl;
        private String description;
    }
}
