package com.poolygo.quiz.domain.factory;

import com.poolygo.quiz.domain.*;
import com.poolygo.quiz.presentation.dto.request.question.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionFactory {

    private final ChoiceItemFactory choiceItemFactory;

    public ImageMcqQuestion from(final ImageMcqQuestionCreateRequest request) {
        String imageUrl = request.getImageUrl();
        List<Integer> answers = request.getAnswers();

        return new ImageMcqQuestion(imageUrl, answers);
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
        ChoiceItem firstChoice = choiceItemFactory.from(request.getFirst());
        ChoiceItem secondChoice = choiceItemFactory.from(request.getSecond());

        return new BinaryChoiceQuestion(firstChoice, secondChoice);
    }

}
