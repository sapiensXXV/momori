package com.poolygo.quiz.presentation.dto.response.detail;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageMcqQuestionDetailResponse extends QuestionDetailResponse {

    private String imageUrl;
    private List<McqChoiceDetail> choices;
}
