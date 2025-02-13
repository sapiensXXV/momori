package com.poolygo.quiz.domain;

import com.poolygo.quiz.presentation.dto.request.quiz.QuizResultRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ImageSubjectiveQuestion extends Question {
    private String imageUrl;
    private List<String> answers;

    public ImageSubjectiveQuestion(
        final String imageUrl,
        final List<String> answers
    ) {
        this.imageUrl = imageUrl;
        this.answers = answers;
    }

    @Override
    public void reflectQuizResult(QuizResultRequest request) {

    }
}
