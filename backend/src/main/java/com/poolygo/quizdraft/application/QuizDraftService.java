package com.poolygo.quizdraft.application;

import com.poolygo.global.exception.DraftException;
import com.poolygo.global.exception.ExceptionCode;
import com.poolygo.quizdraft.domain.ImageMcqChoiceDraft;
import com.poolygo.quizdraft.domain.ImageMcqQuestionDraft;
import com.poolygo.quizdraft.domain.QuestionDraft;
import com.poolygo.quizdraft.domain.QuizDraft;
import com.poolygo.quizdraft.domain.factory.QuizDraftFactory;
import com.poolygo.quizdraft.infrastructure.QuizDraftRepository;
import com.poolygo.quizdraft.presentation.dto.CreateDraftRequest;
import com.poolygo.quizdraft.presentation.dto.response.DraftImageMcqResponse;
import com.poolygo.quizdraft.presentation.dto.response.DraftImageMcqResponse.DraftImageMcqChoiceResponse;
import com.poolygo.quizdraft.presentation.dto.response.DraftImageMcqResponse.DraftImageMcqQuestionResponse;
import com.poolygo.quizdraft.presentation.dto.response.DraftInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizDraftService {

    private final QuizDraftFactory draftFactory;
    private final QuizDraftRepository quizDraftRepository;

    public DraftImageMcqResponse findOneImageMcqDraft(
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

        return DraftImageMcqResponse.of(
            findDraft.getId(),
            findDraft.getType().name(),
            findDraft.getThumbnailUrl(),
            findDraft.getTitle(),
            findDraft.getDescription(),
            questions
        );
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

    public List<DraftInfoResponse> findSimpleByAuth(
        final String identifier,
        final String provider
    ) {
        List<QuizDraft> findByAuth = quizDraftRepository.findAllByUserInfo(identifier, provider);
        return findByAuth.stream()
            .map(draft -> DraftInfoResponse.of(draft.getId(), draft.getType().name() ,  draft.getTitle(),  draft.getCreatedAt()))
            .toList();
    }

//    public String createImageMcqDraft(
//        final CreateDraftRequest request,
//        final String userIdentifier,
//        final String userProvider
//    ) {
//        QuizDraft saveDraft = draftFactory.from(request, userIdentifier, userProvider);
//        QuizDraft savedDraft = quizDraftRepository.save(saveDraft);
//        return savedDraft.getId();
//    }
//
//    public String updateImageMcqDraft(
//        final String id,
//        final CreateDraftRequest request,
//        final String userIdentifier,
//        final String userProvider
//    ) {
//        QuizDraft updatedDraft = draftFactory.from(request, userIdentifier, userProvider);
//        quizDraftRepository.save(updatedDraft);
//        return updatedDraft.getId();
//    }

    public String saveOrUpdateDraft(
        final CreateDraftRequest request,
        final String userIdentifier,
        final String userProvider
    ) {
        // 기존에는 formerId 여부로 생성 메서드와 업데이트 메서드를 나누어서 호출했다. 하지만
        // 팩토리 내부에서 직접 ID를 확인하고 저장하면서 그 과정이 필요 없어졌다.
        QuizDraft savedDraft = draftFactory.from(request, userIdentifier, userProvider);
        return savedDraft.getId();
    }


}
