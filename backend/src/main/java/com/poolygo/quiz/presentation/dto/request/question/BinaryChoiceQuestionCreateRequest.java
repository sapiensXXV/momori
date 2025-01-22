package com.poolygo.quiz.presentation.dto.request.question;

public class BinaryChoiceQuestionCreateRequest {

    private ChoiceItemRequest first;
    private ChoiceItemRequest second;

    public static class ChoiceItemRequest {
        private String imageUrl;
        private String description;
    }
}
