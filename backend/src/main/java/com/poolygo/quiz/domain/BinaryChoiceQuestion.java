package com.poolygo.quiz.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BinaryChoiceQuestion extends Question {
    private String imageUrl;
    private int firstCount;
    private int secondCount;
}
