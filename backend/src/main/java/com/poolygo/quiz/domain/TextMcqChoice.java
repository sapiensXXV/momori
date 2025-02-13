package com.poolygo.quiz.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TextMcqChoice extends McqChoice {
    private String content;
    private boolean answer;
    private int selectedCount;

    public void addSelectedCount() {
        this.selectedCount++;
    }
}
