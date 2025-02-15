package com.poolygo.quiz.domain;

import com.poolygo.quiz.presentation.dto.request.quiz.QuizResultRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AudioSubjectiveQuestion extends Question {
    private String audioUrl;
    private List<String> answers;

    @Override
    public void reflectQuizResult(QuizResultRequest.QuestionResultRequest request) {

    }
}
