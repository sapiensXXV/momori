package com.poolygo.quiz.presentation.dto.response.detail;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageMcqQuestionDetailResponse extends QuestionDetailResponse {
    private String questionId;
    private String imageUrl;
    private List<McqChoiceDetail> choices;
}
