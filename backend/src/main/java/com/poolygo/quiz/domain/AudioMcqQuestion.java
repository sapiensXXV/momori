package com.poolygo.quiz.domain;


import com.poolygo.quiz.presentation.dto.result.AudioMcqQuizResultRequest.AudioMcqQuestionResultRequest;
import com.poolygo.quiz.presentation.dto.result.QuestionResultRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.TypeAlias;

import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TypeAlias("audioMcqQuestion")
public class AudioMcqQuestion extends Question {
    private String audioId;
    private int startTime;
    private int playDuration;
    private List<TextMcqChoice> choices;

    @Override
    public void reflectQuizResult(QuestionResultRequest request) {
        AudioMcqQuestionResultRequest result = (AudioMcqQuestionResultRequest) request;

        this.addTryCount();
        if (result.isCorrect()) {
            this.addCorrectCount();
        }

        List<Integer> selectedChoices = result.getChoices();
        try {
            selectedChoices.forEach((index) -> choices.get(index).addSelectedCount());
        } catch (Exception e) {
            throw new IllegalArgumentException("결과를 반영하던 중 문제가 발생하였습니다.");
        }

    }
}
