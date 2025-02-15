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
public class AudioMcqQuestion extends Question {
    private String audioUrl;
    private List<TextMcqChoice> choices;

    @Override
    public void reflectQuizResult(QuizResultRequest.QuestionResultRequest request) {

    }
}
