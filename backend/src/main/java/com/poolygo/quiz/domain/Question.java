package com.poolygo.quiz.domain;


import com.poolygo.quiz.presentation.dto.request.quiz.QuizResultRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Question {
    private String questionId;
    private int tryCount;
    private int correctCount;

    public void addTryCount() {
        this.tryCount++;
    }

    public void addCorrectCount() {
        this.correctCount++;
    }

    public abstract void reflectQuizResult(QuizResultRequest.QuestionResultRequest request);
}
