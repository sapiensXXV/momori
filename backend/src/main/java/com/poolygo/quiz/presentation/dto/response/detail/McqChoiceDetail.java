package com.poolygo.quiz.presentation.dto.response.detail;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class McqChoiceDetail {

    private String content;
    private boolean answer;
    private int selectedCount;
}