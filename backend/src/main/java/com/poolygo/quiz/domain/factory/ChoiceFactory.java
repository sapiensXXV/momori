package com.poolygo.quiz.domain.factory;


import com.poolygo.quiz.domain.BinaryChoiceItem;
import com.poolygo.quiz.domain.TextMcqChoice;
import com.poolygo.quiz.presentation.dto.request.question.BinaryChoiceQuestionCreateRequest.ChoiceItemRequest;
import com.poolygo.quiz.presentation.dto.request.question.ImageMcqChoiceCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class ChoiceFactory {

    public TextMcqChoice from(ImageMcqChoiceCreateRequest request) {
        String content = request.getContent();
        boolean isAnswer = request.isAnswer();

        return new TextMcqChoice(content, isAnswer, 0);
    }

    public BinaryChoiceItem from(ChoiceItemRequest request) {
        return new BinaryChoiceItem(request.getImageUrl(), request.getDescription());
    }
}
