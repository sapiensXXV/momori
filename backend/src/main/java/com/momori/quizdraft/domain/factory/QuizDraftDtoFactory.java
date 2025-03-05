package com.momori.quizdraft.domain.factory;


import com.momori.quizdraft.domain.*;
import com.momori.quizdraft.presentation.dto.audiomcq.DraftAudioMcqChoiceResponse;
import com.momori.quizdraft.presentation.dto.audiomcq.DraftAudioMcqDetailResponse;
import com.momori.quizdraft.presentation.dto.audiomcq.DraftAudioMcqQuestionResponse;
import com.momori.quizdraft.presentation.dto.audiosubjective.DraftAudioSubDetailResponse;
import com.momori.quizdraft.presentation.dto.audiosubjective.DraftAudioSubQuestionResponse;
import com.momori.quizdraft.presentation.dto.imgsubjective.DraftImageSubQuestionResponse;
import com.momori.quizdraft.presentation.dto.imgsubjective.DraftImageSubQuizDetailResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class QuizDraftDtoFactory {

    public DraftImageSubQuizDetailResponse toDraftImageSubDetailResponse(QuizDraft draft) {

        List<DraftImageSubQuestionResponse> questions = draft.getQuestions().stream()
            .map(this::convertToDraftImageSubQuestionDetailResponse)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();

        return DraftImageSubQuizDetailResponse.builder()
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

    public DraftAudioMcqDetailResponse toDraftAudioMcqDetailResponse(QuizDraft draft) {
        List<DraftAudioMcqQuestionResponse> questions = draft.getQuestions().stream()
            .map(this::convertToDraftAudioMcqQuestionResponse)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();

        return DraftAudioMcqDetailResponse.builder()
            .draftId(draft.getId())
            .quizType(draft.getType().name())
            .thumbnailUrl(draft.getThumbnailUrl())
            .title(draft.getTitle())
            .description(draft.getDescription())
            .questions(questions)
            .build();
    }

    private Optional<DraftAudioMcqQuestionResponse> convertToDraftAudioMcqQuestionResponse(QuestionDraft questionDraft) {


        if (questionDraft instanceof AudioMcqQuestionDraft) {
            AudioMcqQuestionDraft data = (AudioMcqQuestionDraft) questionDraft;
            List<DraftAudioMcqChoiceResponse> choices = data.getChoices().stream()
                .map(this::convertToDraftAudioMcqChoiceResponse)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

            return Optional.of(new DraftAudioMcqQuestionResponse(data.getAudioId(), data.getStartTime(), data.getPlayDuration(), choices));
        }
        return Optional.empty();
    }

    private Optional<DraftAudioMcqChoiceResponse> convertToDraftAudioMcqChoiceResponse(McqChoiceDraft choiceDraft) {
        if (choiceDraft instanceof AudioMcqChoiceDraft) {
            AudioMcqChoiceDraft data = (AudioMcqChoiceDraft) choiceDraft;
            return Optional.of(new DraftAudioMcqChoiceResponse(data.getContent(), data.isAnswer()));
        }

        return Optional.empty();

    }


    /**
     * @param draft 찾은 임시저장 데이터, 오디오-주관식 유형의 임시저장 데이터임을 가정한다.
     * @return 사용자에게 반환할 오디오-주관식 유형의 임시저장 데이터
     */
    public DraftAudioSubDetailResponse toDraftAudioSubDetailResponse(QuizDraft draft) {
        List<DraftAudioSubQuestionResponse> questions = draft.getQuestions().stream()
            .map(this::convertToDraftAudioSubQuestionResponse)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();

        return DraftAudioSubDetailResponse.builder()
            .draftId(draft.getId())
            .quizType(draft.getType().name())
            .thumbnailUrl(draft.getThumbnailUrl())
            .title(draft.getTitle())
            .description(draft.getDescription())
            .questions(questions)
            .build();
    }

    private Optional<DraftAudioSubQuestionResponse> convertToDraftAudioSubQuestionResponse(QuestionDraft questionDraft) {
        if (questionDraft instanceof AudioSubQuestionDraft) {
            AudioSubQuestionDraft data = (AudioSubQuestionDraft) questionDraft;
            return Optional.of(new DraftAudioSubQuestionResponse(
                data.getAudioId(),
                data.getStartTime(),
                data.getPlayDuration(),
                data.getAnswers()
            ));
        }
        return Optional.empty();
    }
}
