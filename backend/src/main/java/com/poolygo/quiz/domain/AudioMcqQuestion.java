package com.poolygo.quiz.domain;


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
        // TODO: 오디오-객관식 퀴즈 결과 반영 메서드
    }
}
