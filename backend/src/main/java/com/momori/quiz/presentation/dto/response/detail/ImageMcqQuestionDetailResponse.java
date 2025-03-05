package com.momori.quiz.presentation.dto.response.detail;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ImageMcqQuestionDetailResponse extends QuestionDetailResponse {
    private String imageUrl;
    private List<McqChoiceDetail> choices;
}
