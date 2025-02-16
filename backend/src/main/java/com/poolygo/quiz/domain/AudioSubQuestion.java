package com.poolygo.quiz.domain;

import com.poolygo.quiz.presentation.dto.result.ImageMcqQuizResultRequest;
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
    private String audioUrl;
    private List<String> answers;

    @Override
    public void reflectQuizResult(ImageMcqQuizResultRequest.ImageMcqQuestionResultRequest request) {

    }
}
