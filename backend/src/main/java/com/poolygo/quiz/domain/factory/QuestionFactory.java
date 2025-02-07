package com.poolygo.quiz.domain.factory;

import com.poolygo.quiz.domain.*;
import com.poolygo.quiz.presentation.dto.request.question.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionFactory {

    private final ChoiceFactory choiceFactory;

    public ImageMcqQuestion from(final ImageMcqQuestionCreateRequest request) {
        String imageUrl = request.getImageUrl();
        List<ImageMcqChoice> choices = request.getChoices().stream()
            .map(choiceFactory::from)
            .toList();
        return new ImageMcqQuestion(imageUrl, choices);
    }

    public ImageSubjectiveQuestion from(final ImageSubjectiveQuestionCreateRequest request) {
        String imageUrl = request.getImageUrl();
        List<String> answers = request.getAnswers();

        return new ImageSubjectiveQuestion(imageUrl, answers);
    }

    public AudioMcqQuestion from(final AudioMcqQuestionCreateRequest request) {
        String audioUrl = request.getAudioUrl();
        List<Integer> answers = request.getAnswers();

        return new AudioMcqQuestion(audioUrl, answers);
    }

    public AudioSubjectiveQuestion from(final AudioSubjectiveQuestionCreateRequest request) {
        String audioUrl = request.getAudioUrl();
        List<String> answers = request.getAnswers();

        return new AudioSubjectiveQuestion(audioUrl, answers);
    }

    public BinaryChoiceQuestion from(final BinaryChoiceQuestionCreateRequest request) {
        BinaryChoiceItem firstChoice = choiceFactory.from(request.getFirst());
        BinaryChoiceItem secondChoice = choiceFactory.from(request.getSecond());

        return new BinaryChoiceQuestion(firstChoice, secondChoice);
    }

}
