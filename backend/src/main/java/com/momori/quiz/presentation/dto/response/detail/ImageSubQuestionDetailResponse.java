package com.momori.quiz.presentation.dto.response.detail;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@AllArgsConstructor
@SuperBuilder
public class ImageSubQuestionDetailResponse extends QuestionDetailResponse {
    private String imageUrl;
    private List<String> answers;
}
