package com.poolygo.quiz.domain;

import com.poolygo.quiz.presentation.dto.result.ImageMcqQuizResultRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.TypeAlias;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TypeAlias("binaryChoiceQuestion")
public class BinaryChoiceQuestion extends Question {
    private BinaryChoiceItem first;
    private BinaryChoiceItem second;

    @Override
    public void reflectQuizResult(ImageMcqQuizResultRequest.ImageMcqQuestionResultRequest request) {

    }
}
