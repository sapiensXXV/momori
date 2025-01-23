package com.poolygo.quiz.domain;


import lombok.Data;

@Data
public abstract class Question {
    private String questionId;
    private int tryCount;
    private int correctCount;

    public Question() {
        this.tryCount = 0;
        this.correctCount = 0;
    }
}
