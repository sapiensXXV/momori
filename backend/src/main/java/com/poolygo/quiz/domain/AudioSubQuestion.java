package com.poolygo.quiz.domain;

import com.poolygo.quiz.presentation.dto.result.AudioSubQuizResultRequest.AudioSubQuestionResultRequest;
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
@TypeAlias("audioSubQuestion")
public class AudioSubQuestion extends Question {
    private String audioId;
    private int startTime;
    private int playDuration;
    private List<String> answers;

    @Override
    public void reflectQuizResult(QuestionResultRequest request) {
        AudioSubQuestionResultRequest result = (AudioSubQuestionResultRequest) request;

        this.addTryCount();
        if (result.isCorrect()) {
            this.addCorrectCount();
        }
    }
}
