package com.poolygo.quizdraft.application;

import com.poolygo.quizdraft.domain.QuizDraft;
import com.poolygo.quizdraft.domain.factory.QuizDraftFactory;
import com.poolygo.quizdraft.infrastructure.QuizDraftRepository;
import com.poolygo.quizdraft.presentation.dto.DraftImageMcqQuizRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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


}
