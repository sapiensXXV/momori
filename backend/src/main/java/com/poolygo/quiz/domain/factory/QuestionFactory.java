package com.poolygo.quiz.domain.factory;


import com.poolygo.quiz.domain.ImageMcqQuestion;
import com.poolygo.quiz.presentation.dto.request.question.ImageMcqQuestionCreateRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionFactory {

    public ImageMcqQuestion from(final ImageMcqQuestionCreateRequest request) {
        String imageUrl = request.getImageUrl();
        List<Integer> answers = request.getAnswers();

        return new ImageMcqQuestion(imageUrl, answers);
    }

}
