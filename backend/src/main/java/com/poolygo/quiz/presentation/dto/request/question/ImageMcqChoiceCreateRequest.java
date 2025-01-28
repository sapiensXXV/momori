package com.poolygo.quiz.presentation.dto.request.question;

import com.poolygo.quiz.domain.McqChoice;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ImageMcqChoiceCreateRequest extends McqChoice {
    private String content;
    private boolean isAnswer;
}
