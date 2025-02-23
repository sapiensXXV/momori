package com.poolygo.quiz.domain;

import com.poolygo.quiz.presentation.dto.result.ImageSubQuizResultRequest.ImageSubQuestionResultRequest;
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
@TypeAlias("imageSubQuestion")
public class ImageSubQuestion extends Question {
    private String imageUrl;
    private List<String> answers;

    @Override
    public void reflectQuizResult(QuestionResultRequest request) {
        ImageSubQuestionResultRequest result = (ImageSubQuestionResultRequest) request;

        this.addTryCount();
        if (result.isCorrect()) {
            this.addCorrectCount();

        }
    }
}
