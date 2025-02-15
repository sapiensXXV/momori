package com.poolygo.quizdraft.domain.factory;


import com.poolygo.quizdraft.domain.ImageMcqChoiceDraft;
import com.poolygo.quizdraft.domain.ImageMcqQuestionDraft;
import com.poolygo.quizdraft.domain.ImageSubQuestionDraft;
import com.poolygo.quizdraft.domain.QuestionDraft;
import com.poolygo.quizdraft.presentation.dto.imgsubjective.DraftImageSubQuizRequest;
import com.poolygo.quizdraft.presentation.dto.imagemcq.DraftImageMcqChoiceRequest;
import com.poolygo.quizdraft.presentation.dto.imagemcq.DraftImageMcqQuestionRequest;
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


}
