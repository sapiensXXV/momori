package com.momori.quiz.presentation.dto.request.question;

import com.momori.quiz.domain.McqChoice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@Getter
@AllArgsConstructor
@ToString
public class AudioMcqChoiceCreateRequest extends McqChoice {
    private String content;
    private boolean answer;
}
