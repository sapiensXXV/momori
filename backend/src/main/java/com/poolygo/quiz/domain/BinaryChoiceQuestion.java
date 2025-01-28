package com.poolygo.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BinaryChoiceQuestion extends Question {
    private BinaryChoiceItem first;
    private BinaryChoiceItem second;
}
