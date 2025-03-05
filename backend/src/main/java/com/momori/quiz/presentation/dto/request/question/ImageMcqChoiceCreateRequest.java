package com.momori.quiz.presentation.dto.request.question;

import com.momori.quiz.domain.McqChoice;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ImageMcqChoiceCreateRequest extends McqChoice {
    private String content;
    private boolean answer;
}
