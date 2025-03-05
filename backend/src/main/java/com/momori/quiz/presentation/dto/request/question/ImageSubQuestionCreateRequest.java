package com.momori.quiz.presentation.dto.request.question;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ImageSubQuestionCreateRequest {
    private String imageUrl;
    private List<String> answers;
}
