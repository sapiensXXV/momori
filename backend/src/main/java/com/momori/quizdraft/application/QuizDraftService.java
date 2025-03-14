package com.momori.quizdraft.application;

import com.momori.global.exception.DraftException;
import com.momori.global.exception.ExceptionCode;
import com.momori.quizdraft.domain.ImageMcqChoiceDraft;
import com.momori.quizdraft.domain.ImageMcqQuestionDraft;
import com.momori.quizdraft.domain.QuestionDraft;
import com.momori.quizdraft.domain.QuizDraft;
import com.momori.quizdraft.domain.factory.QuizDraftDtoFactory;
import com.momori.quizdraft.domain.factory.QuizDraftFactory;
import com.momori.quizdraft.infrastructure.QuizDraftRepository;
import com.momori.quizdraft.presentation.dto.DraftRequest;
import com.momori.quizdraft.presentation.dto.DraftSimpleResponse;
import com.momori.quizdraft.presentation.dto.audiomcq.DraftAudioMcqDetailResponse;
import com.momori.quizdraft.presentation.dto.audiosubjective.DraftAudioSubDetailResponse;
import com.momori.quizdraft.presentation.dto.imagemcq.DraftImageMcqDetailResponse;
import com.momori.quizdraft.presentation.dto.imagemcq.DraftImageMcqDetailResponse.DraftImageMcqChoiceResponse;
import com.momori.quizdraft.presentation.dto.imagemcq.DraftImageMcqDetailResponse.DraftImageMcqQuestionResponse;
import com.momori.quizdraft.presentation.dto.imgsubjective.DraftImageSubQuizDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizDraftService {

    private final QuizDraftFactory draftFactory;
    private final QuizDraftRepository quizDraftRepository;
    private final QuizDraftDtoFactory quizDraftDtoFactory;

    public DraftImageMcqDetailResponse findImageMcqDraft(
        final String draftId,
        final String userIdentifier,
        final String userProvider
    ) {
        QuizDraft findDraft = quizDraftRepository
            .findByIdAndUserInfo(draftId, userIdentifier, userProvider)
            .orElseThrow(() -> new DraftException(ExceptionCode.INVALID_DRAFT_ID));

        List<DraftImageMcqQuestionResponse> questions = findDraft.getQuestions().stream()
            .map(this::convertToQuestionResponse)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();

        return DraftImageMcqDetailResponse.of(
            findDraft.getId(),
            findDraft.getType().name(),
            findDraft.getThumbnailUrl(),
            findDraft.getTitle(),
            findDraft.getDescription(),
            questions
        );
    }

    public DraftImageSubQuizDetailResponse findImageSubDraft(
        final String draftId,
        final String userIdentifier,
        final String provider
    ) {
        QuizDraft findDraft = quizDraftRepository.findByIdAndUserInfo(draftId, userIdentifier, provider)
            .orElseThrow(() -> new DraftException(ExceptionCode.INVALID_DRAFT_ID));

        return quizDraftDtoFactory.toDraftImageSubDetailResponse(findDraft);
    }

    private Optional<DraftImageMcqQuestionResponse> convertToQuestionResponse(QuestionDraft d) {
        if (d instanceof ImageMcqQuestionDraft draft) {
            List<DraftImageMcqChoiceResponse> choices = draft.getChoices().stream()
                .map(c -> convertToChoiceResponse((ImageMcqChoiceDraft) c))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

            return Optional.of(DraftImageMcqQuestionResponse.of(draft.getImageUrl(), choices));
        }
        return Optional.empty();
    }

    private Optional<DraftImageMcqChoiceResponse> convertToChoiceResponse(ImageMcqChoiceDraft choice) {
        return Optional.of(DraftImageMcqChoiceResponse.of(choice.getContent(), choice.isAnswer()));
    }

    public DraftAudioMcqDetailResponse findAudioMcqDraft(
        final String draftId,
        final String userIdentifier,
        final String userProvider
    ) {
        QuizDraft findDraft = quizDraftRepository
            .findByIdAndUserInfo(draftId, userIdentifier, userProvider)
            .orElseThrow(() -> new DraftException(ExceptionCode.INVALID_DRAFT_ID));

        return quizDraftDtoFactory.toDraftAudioMcqDetailResponse(findDraft);
    }

    public DraftAudioSubDetailResponse findAudioSubDraft(
        final String draftId,
        final String userIdentifier,
        final String userProvider
    ) {
        QuizDraft findDraft = quizDraftRepository
            .findByIdAndUserInfo(draftId, userIdentifier, userProvider)
            .orElseThrow(() -> new DraftException(ExceptionCode.INVALID_DRAFT_ID));

        return quizDraftDtoFactory.toDraftAudioSubDetailResponse(findDraft);
    }

    public List<DraftSimpleResponse> findSimpleByAuth(
        final String identifier,
        final String provider
    ) {
        List<QuizDraft> findByAuth = quizDraftRepository.findAllByUserInfo(identifier, provider);
        return findByAuth.stream()
            .map(draft -> DraftSimpleResponse.of(draft.getId(), draft.getType().name() ,  draft.getTitle(),  draft.getCreatedAt()))
            .toList();
    }

    public String saveOrUpdateDraft(
        final DraftRequest request,
        final String userIdentifier,
        final String userProvider
    ) {
        // 기존에는 formerId 여부로 생성 메서드와 업데이트 메서드를 나누어서 호출했다. 하지만
        // 팩토리 내부에서 직접 ID를 확인하고 저장하면서 그 과정이 필요 없어졌다.
        QuizDraft savedDraft = draftFactory.from(request, userIdentifier, userProvider);
        quizDraftRepository.save(savedDraft);
        return savedDraft.getId();
    }




}
