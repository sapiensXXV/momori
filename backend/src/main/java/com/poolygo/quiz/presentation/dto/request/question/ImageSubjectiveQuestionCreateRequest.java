package com.poolygo.quiz.presentation.dto.request.question;

import lombok.Getter;

import java.util.List;

@Getter
public class ImageSubjectiveQuestionCreateRequest {
    private String imageUrl;
    private List<String> answers;
}
