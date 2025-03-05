package com.momori.quiz.domain.factory;


import com.momori.quiz.domain.BinaryChoiceItem;
import com.momori.quiz.domain.TextMcqChoice;
import com.momori.quiz.presentation.dto.request.question.AudioMcqChoiceCreateRequest;
import com.momori.quiz.presentation.dto.request.question.BinaryChoiceQuestionCreateRequest.ChoiceItemRequest;
import com.momori.quiz.presentation.dto.request.question.ImageMcqChoiceCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class ChoiceFactory {

    public TextMcqChoice from(ImageMcqChoiceCreateRequest request) {
        String content = request.getContent();
        boolean isAnswer = request.isAnswer();

        return new TextMcqChoice(content, isAnswer, 0);
    }

    public TextMcqChoice from(AudioMcqChoiceCreateRequest request) {
        return new TextMcqChoice(request.getContent(), request.isAnswer(), 0);
    }

    public BinaryChoiceItem from(ChoiceItemRequest request) {
        return new BinaryChoiceItem(request.getImageUrl(), request.getDescription());
    }
}
