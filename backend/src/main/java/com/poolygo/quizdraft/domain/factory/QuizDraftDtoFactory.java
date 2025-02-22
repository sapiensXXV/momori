package com.poolygo.quizdraft.domain.factory;


import com.poolygo.quizdraft.domain.ImageSubQuestionDraft;
import com.poolygo.quizdraft.domain.QuestionDraft;
import com.poolygo.quizdraft.domain.QuizDraft;
import com.poolygo.quizdraft.presentation.dto.imgsubjective.DraftImageSubQuestionResponse;
import com.poolygo.quizdraft.presentation.dto.imgsubjective.DraftImageSubQuizResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class QuizDraftDtoFactory {

    public DraftImageSubQuizResponse toDraftImageSubDetailResponse(QuizDraft draft) {

        List<DraftImageSubQuestionResponse> questions = draft.getQuestions().stream()
            .map(this::convertToDraftImageSubQuestionDetailResponse)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();

        return DraftImageSubQuizResponse.builder()
            .draftId(draft.getId())
            .quizType(draft.getType().name())
            .thumbnailUrl(draft.getThumbnailUrl())
            .title(draft.getTitle())
            .description(draft.getDescription())
            .questions(questions)
            .build();
    }

    private Optional<DraftImageSubQuestionResponse> convertToDraftImageSubQuestionDetailResponse(QuestionDraft questionDraft) {
        if (questionDraft instanceof ImageSubQuestionDraft) {
            ImageSubQuestionDraft data = (ImageSubQuestionDraft) questionDraft;
            return Optional.of(new DraftImageSubQuestionResponse(data.getImageUrl(), data.getAnswers()));
        }
        return Optional.empty();
    }

}
