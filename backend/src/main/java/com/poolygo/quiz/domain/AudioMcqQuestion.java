package com.poolygo.quiz.domain;


import com.poolygo.quiz.presentation.dto.request.quiz.QuizResultRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AudioMcqQuestion extends Question {
    private String audioUrl;
    private List<Integer> answers;

    @Override
    public void reflectQuizResult(QuizResultRequest request) {

    }
}
