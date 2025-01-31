package com.poolygo.quizdraft.application;

import com.poolygo.quizdraft.domain.QuizDraft;
import com.poolygo.quizdraft.domain.factory.QuizDraftFactory;
import com.poolygo.quizdraft.infrastructure.QuizDraftRepository;
import com.poolygo.quizdraft.presentation.dto.DraftImageMcqQuizRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class QuizDraftService {

    private final QuizDraftFactory draftFactory;
    private final QuizDraftRepository quizDraftRepository;

    public String createImageMcqDraft(
        final DraftImageMcqQuizRequest request,
        final String userIdentifier,
        final String userProvider
    ) {
        QuizDraft savedDraft = quizDraftRepository.save(draftFactory.from(request, userIdentifier, userProvider));
        return savedDraft.getId();
    }

    public String updateImageMcqDraft(
        final String id,
        final DraftImageMcqQuizRequest request,
        final String userIdentifier,
        final String userProvider
    ) {
        QuizDraft updatedDraft = draftFactory.from(id, request, userIdentifier, userProvider);
        quizDraftRepository.save(updatedDraft);
        return updatedDraft.getId();
    }

    public String saveOrUpdateDraft(
        final DraftImageMcqQuizRequest request,
        final String userIdentifier,
        final String userProvider
    ) {
        String formerId = request.getFormerDraftId();
        if (!StringUtils.hasText(formerId)) {
            // 기존의 draft_id가 없는 경우 새로운 도큐먼트 생성
            return createImageMcqDraft(request, userIdentifier, userProvider);
        } else {
            return updateImageMcqDraft(formerId, request, userIdentifier, userProvider);
        }
    }


}
