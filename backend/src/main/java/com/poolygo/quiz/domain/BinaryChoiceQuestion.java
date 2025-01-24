package com.poolygo.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BinaryChoiceQuestion extends Question {
    private ChoiceItem first;
    private ChoiceItem second;
}
