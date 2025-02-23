package com.poolygo.quizdraft.domain.factory;

import com.poolygo.global.exception.DraftException;
import com.poolygo.global.exception.ExceptionCode;
import com.poolygo.quiz.domain.QuizType;
import com.poolygo.quiz.domain.factory.UserInfoFactory;
import com.poolygo.quizdraft.domain.QuestionDraft;
import com.poolygo.quizdraft.domain.QuizDraft;
import com.poolygo.quizdraft.presentation.dto.DraftRequest;
import com.poolygo.quizdraft.presentation.dto.audiomcq.DraftAudioMcqQuizRequest;
import com.poolygo.quizdraft.presentation.dto.audiosubjective.DraftAudioSubQuizRequest;
import com.poolygo.quizdraft.presentation.dto.imagemcq.DraftImageMcqQuizRequest;
import com.poolygo.quizdraft.presentation.dto.imgsubjective.DraftImageSubQuizRequest;
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
        final DraftRequest request,
        final String identifier,
        final String provider
    ) {

        QuizType type = QuizType.from(request.getType());
        switch (type) {
            case IMAGE_MCQ -> {
                return fromImageMcqQuizDraft((DraftImageMcqQuizRequest) request, identifier, provider);
            }
            case IMAGE_SUBJECTIVE -> {
                return fromImageSubQuizDraft((DraftImageSubQuizRequest) request, identifier, provider);
            }
            // TODO: 나머지 퀴즈 타입 구현
            case AUDIO_MCQ -> {
                return fromAudioMcqQuizDraft((DraftAudioMcqQuizRequest) request, identifier, provider);
            }
            case AUDIO_SUBJECTIVE -> {
                return fromAudioSubQuizDraft((DraftAudioSubQuizRequest) request, identifier, provider);
            }
            case BINARY_CHOICE -> {

            }
        }

        throw new DraftException(ExceptionCode.DRAFT_SAVE_FAIL);
    }

    private QuizDraft fromImageMcqQuizDraft(
        final DraftImageMcqQuizRequest request,
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

    private QuizDraft fromImageSubQuizDraft(
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
    private QuizDraft fromAudioMcqQuizDraft(
        final DraftAudioMcqQuizRequest request,
        final String identifier,
        final String provider
    ) {
        List<QuestionDraft> questions = request.getQuestions().stream()
            .map(questionDraftFactory::from)
            .toList();

        return QuizDraft.builder()
            .id(StringUtils.hasText(request.getFormerDraftId()) ? request.getFormerDraftId() : null)
            .title(request.getTitle())
            .thumbnailUrl(request.getThumbnailUrl())
            .description(request.getDescription())
            .userInfo(userInfoFactory.from(identifier, provider))
            .type(QuizType.from(request.getType()))
            .questions(questions)
            .build();

    }

    private QuizDraft fromAudioSubQuizDraft(
        final DraftAudioSubQuizRequest request,
        final String identifier,
        final String provider
    ) {
        List<QuestionDraft> questions = request.getQuestions().stream()
            .map(questionDraftFactory::from)
            .toList();

        return QuizDraft.builder()
            .id(StringUtils.hasText(request.getFormerDraftId()) ? request.getFormerDraftId() : null)
            .title(request.getTitle())
            .thumbnailUrl(request.getThumbnailUrl())
            .description(request.getDescription())
            .userInfo(userInfoFactory.from(identifier, provider))
            .type(QuizType.from(request.getType()))
            .questions(questions)
            .build();

    }


}
