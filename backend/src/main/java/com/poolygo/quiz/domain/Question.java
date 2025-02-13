package com.poolygo.quiz.domain;


import com.poolygo.quiz.presentation.dto.request.quiz.QuizResultRequest;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class Question {
    private String questionId;
    private int tryCount;
    private int correctCount;

    public Question() {
        this.tryCount = 0;
        this.correctCount = 0;
    }

    public void addTryCount() {
        this.tryCount++;
    }

    public void addCorrectCount() {
        this.correctCount++;
    }

    public abstract void reflectQuizResult(QuizResultRequest.QuestionResultRequest request);
}
