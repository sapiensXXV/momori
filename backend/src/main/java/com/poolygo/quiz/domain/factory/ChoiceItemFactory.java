package com.poolygo.quiz.domain.factory;


import com.poolygo.quiz.domain.ChoiceItem;
import com.poolygo.quiz.presentation.dto.request.question.BinaryChoiceQuestionCreateRequest.ChoiceItemRequest;
import org.springframework.stereotype.Component;

@Component
public class ChoiceItemFactory {

    public ChoiceItem from(ChoiceItemRequest request) {
        return new ChoiceItem(request.getImageUrl(), request.getDescription());
    }

}
