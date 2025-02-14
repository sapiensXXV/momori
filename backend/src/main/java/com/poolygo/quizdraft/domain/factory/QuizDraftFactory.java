package com.poolygo.quizdraft.domain.factory;

import com.poolygo.global.exception.DraftException;
import com.poolygo.global.exception.ExceptionCode;
import com.poolygo.quiz.domain.QuizType;
import com.poolygo.quiz.domain.factory.UserInfoFactory;
import com.poolygo.quizdraft.domain.QuestionDraft;
import com.poolygo.quizdraft.domain.QuizDraft;
import com.poolygo.quizdraft.presentation.dto.CreateDraftRequest;
import com.poolygo.quizdraft.presentation.dto.imgsubjective.DraftImageSubQuizRequest;
import com.poolygo.quizdraft.presentation.dto.request.CreateDraftImageMcqQuizRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;


@Component
@RequiredArgsConstructor
public class QuizDraftFactory {

    private final QuestionDraftFactory questionDraftFactory;
    private final UserInfoFactory userInfoFactory;

    public QuizDraft from(
        final CreateDraftRequest request,
        final String identifier,
        final String provider
    ) {

        QuizType type = QuizType.from(request.getType());
        switch (type) {
            case IMAGE_MCQ -> {
                return toImageMcqQuizDraft((CreateDraftImageMcqQuizRequest) request, identifier, provider);
            }
            case IMAGE_SUBJECTIVE -> {
                return toImageSubQuizDraft((DraftImageSubQuizRequest) request, identifier, provider);
            }
            // TODO: 나머지 퀴즈 타입 구현
            case AUDIO_MCQ -> {

            }
            case AUDIO_SUBJECTIVE -> {

            }
            case BINARY_CHOICE -> {

            }
        }

        throw new DraftException(ExceptionCode.DRAFT_SAVE_FAIL);
    }

    public QuizDraft toImageMcqQuizDraft(
        final CreateDraftImageMcqQuizRequest request,
        final String userIdentifier,
        final String userProvider
    ) {
        List<QuestionDraft> questions = request.getQuestions().stream()
            .map(questionDraftFactory::from)
            .toList();

        return QuizDraft.builder()
            .id(StringUtils.hasText(request.getFormerDraftId()) ? request.getFormerDraftId() : null)
            .title(request.getTitle())
            .thumbnailUrl(request.getThumbnailUrl())
            .description(request.getDescription())
            .userInfo(userInfoFactory.from(userIdentifier, userProvider))
            .type(QuizType.from(request.getType()))
            .questions(questions)
            .build();
    }

    public QuizDraft toImageSubQuizDraft(
        final DraftImageSubQuizRequest request,
        final String userIdentifier,
        final String userProvider
    ) {
        List<QuestionDraft> questions = request.getQuestions().stream()
            .map(questionDraftFactory::from)
            .toList();

        return QuizDraft.builder()
            .id(StringUtils.hasText(request.getFormerDraftId()) ? request.getFormerDraftId() : null)
            .title(request.getTitle())
            .thumbnailUrl(request.getThumbnailUrl())
            .description(request.getDescription())
            .userInfo(userInfoFactory.from(userIdentifier, userProvider))
            .type(QuizType.from(request.getType()))
            .questions(questions)
            .build();
    }

}
