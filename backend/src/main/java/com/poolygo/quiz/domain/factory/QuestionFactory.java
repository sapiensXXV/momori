package com.poolygo.quiz.domain.factory;

import com.poolygo.quiz.domain.*;
import com.poolygo.quiz.presentation.dto.request.question.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class QuestionFactory {

    private final ChoiceFactory choiceFactory;

    public ImageMcqQuestion from(final ImageMcqQuestionCreateRequest request) {
        String imageUrl = request.getImageUrl();
        List<TextMcqChoice> choices = request.getChoices().stream()
            .map(choiceFactory::from)
            .toList();
        return ImageMcqQuestion.builder()
            .imageUrl(imageUrl)
            .choices(choices)
            .questionId(UUID.randomUUID().toString())
            .tryCount(0)
            .correctCount(0)
            .build();
    }

    // TODO: 다른 타입의 문제 유형도 Question 추상클래스의 필드를 포함하도록 @SuperBuilder 적용
    public ImageSubQuestion from(final ImageSubQuestionCreateRequest request) {
        String imageUrl = request.getImageUrl();
        List<String> answers = request.getAnswers();

        return ImageSubQuestion.builder()
            .imageUrl(imageUrl)
            .answers(answers)
            .questionId(UUID.randomUUID().toString())
            .tryCount(0)
            .correctCount(0)
            .build();
    }

    public AudioMcqQuestion from(final AudioMcqQuestionCreateRequest request) {
        String audioUrl = request.getAudioUrl();
        List<TextMcqChoice> choices = request.getChoices().stream()
            .map(choiceFactory::from)
            .toList();

        return AudioMcqQuestion.builder()
            .audioUrl(audioUrl)
            .choices(choices)
            .questionId(UUID.randomUUID().toString())
            .tryCount(0)
            .correctCount(0)
            .build();
    }

    // TODO: 오디오-주관식, 이지선다 Question 팩토리 메서드 작성.
    public AudioSubQuestion from(final AudioSubQuestionCreateRequest request) {
        String audioUrl = request.getAudioUrl();
        List<String> answers = request.getAnswers();

        return new AudioSubQuestion(audioUrl, answers);
    }

    public BinaryChoiceQuestion from(final BinaryChoiceQuestionCreateRequest request) {
        BinaryChoiceItem firstChoice = choiceFactory.from(request.getFirst());
        BinaryChoiceItem secondChoice = choiceFactory.from(request.getSecond());

        return new BinaryChoiceQuestion(firstChoice, secondChoice);
    }

}
