package com.poolygo.quiz.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class Question {
    private String questionId;
    private int tryCount;
    private int correctCount;
}
