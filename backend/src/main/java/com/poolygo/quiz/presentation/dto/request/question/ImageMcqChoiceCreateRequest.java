package com.poolygo.quiz.presentation.dto.request.question;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ImageMcqChoiceCreateRequest {
    private String content;
    private boolean isAnswer;
}
