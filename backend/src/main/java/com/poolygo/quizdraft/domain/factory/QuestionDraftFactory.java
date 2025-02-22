package com.poolygo.quizdraft.domain.factory;


import com.poolygo.quizdraft.domain.*;
import com.poolygo.quizdraft.presentation.dto.audiomcq.DraftAudioMcqChoiceRequest;
import com.poolygo.quizdraft.presentation.dto.audiomcq.DraftAudioMcqQuestionRequest;
import com.poolygo.quizdraft.presentation.dto.audiosubjective.DraftAudioSubQuestionRequest;
import com.poolygo.quizdraft.presentation.dto.imagemcq.DraftImageMcqChoiceRequest;
import com.poolygo.quizdraft.presentation.dto.imagemcq.DraftImageMcqQuestionRequest;
import com.poolygo.quizdraft.presentation.dto.imgsubjective.DraftImageSubQuizRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionDraftFactory {

    private final ChoiceDraftFactory choiceDraftFactory;

    public QuestionDraft from(DraftImageMcqQuestionRequest request) {
        String imageUrl = request.getImageUrl();
        List<DraftImageMcqChoiceRequest> choices = request.getChoices();

        List<ImageMcqChoiceDraft> draftChoices = choices.stream()
            .map(choiceDraftFactory::from)
            .toList();

        return ImageMcqQuestionDraft.of(imageUrl, draftChoices);
    }

    public QuestionDraft from(DraftImageSubQuizRequest.DraftImageSubQuestionRequest request) {
        String imageUrl = request.getImageUrl();
        List<String> answers = request.getAnswers();

        return ImageSubQuestionDraft.of(imageUrl, answers);
    }
    
    public QuestionDraft from(DraftAudioMcqQuestionRequest request) {
        String audioId = request.getAudioId();
        int startTime = request.getStartTime();
        int playDuration = request.getPlayDuration();
        List<DraftAudioMcqChoiceRequest> choices = request.getChoices();

        List<AudioMcqChoiceDraft> draftChoices = choices.stream()
            .map(choiceDraftFactory::from)
            .toList();

        return new AudioMcqQuestionDraft(audioId, startTime, playDuration, draftChoices);
    }

    public QuestionDraft from(DraftAudioSubQuestionRequest request) {
        String audioId = request.getAudioId();
        int startTime = request.getStartTime();
        int playDuration = request.getPlayDuration();
        List<String> answers = request.getAnswers();

        return new AudioSubQuestionDraft(audioId, startTime, playDuration, answers);
    }


}
