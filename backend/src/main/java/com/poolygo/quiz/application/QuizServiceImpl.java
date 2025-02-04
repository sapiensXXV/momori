package com.poolygo.quiz.application;

import com.poolygo.auth.dto.UserAuthDto;
import com.poolygo.quiz.domain.Quiz;
import com.poolygo.quiz.domain.factory.QuizFactory;
import com.poolygo.quiz.infrastructure.QuizRepository;
import com.poolygo.quiz.presentation.dto.QuizInfo;
import com.poolygo.quiz.presentation.dto.request.question.ImageMcqQuestionCreateRequest;
import com.poolygo.quiz.presentation.dto.request.quiz.*;
import com.poolygo.quiz.presentation.dto.response.QuizCreateResponse;
import com.poolygo.quizdraft.infrastructure.QuizDraftRepository;
import com.poolygo.s3.S3ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuizDraftRepository draftRepository;
    private final QuizFactory quizFactory;
    private final S3ImageService s3ImageService;

    @Override
    public QuizCreateResponse createImageMcqQuiz(ImageMcqQuizCreateRequest request, UserAuthDto auth) {

        List<ImageMcqQuestionCreateRequest> questions = request.getQuestions();
        List<ImageMcqQuestionCreateRequest> newQuestions = questions.stream()
            .map(question -> {
                String permanentUrl = s3ImageService.copyDraftToPermanent(question.getImageUrl());
                s3ImageService.deleteObject(question.getImageUrl()); // 기존 임시 이미지 삭제
                return new ImageMcqQuestionCreateRequest(
                    permanentUrl,
                    question.getChoices()
                );
            })
            .toList();

        ImageMcqQuizCreateRequest newRequest = new ImageMcqQuizCreateRequest(
            request.getTitle(),
            request.getDraftId(),
            request.getThumbnailUrl(),
            request.getDescription(),
            request.getType(),
            newQuestions
        );

        Quiz newQuiz = quizFactory.from(newRequest, auth);
        Quiz createdQuiz = quizRepository.save(newQuiz);
        if (StringUtils.hasText(request.getDescription())) {
            draftRepository.deleteById(request.getDraftId()); // 임시저장 데이터로부터 퀴즈를 생성한 경우 임시저장 데이터를 삭제
        }

        return new QuizCreateResponse(createdQuiz.getId(), createdQuiz.getTitle());
    }

    @Override
    public QuizCreateResponse createImageSubjectiveQuiz(ImageSubjectiveQuizCreateRequest request, UserAuthDto auth) {
        Quiz newQuiz = quizFactory.from(request, auth);
        Quiz createdQuiz = quizRepository.save(newQuiz);

        return new QuizCreateResponse(createdQuiz.getId(), createdQuiz.getTitle());
    }

    @Override
    public QuizCreateResponse createAudioMcqQuiz(AudioMcqQuizCreateRequest request, UserAuthDto auth) {
        Quiz newQuiz = quizFactory.from(request, auth);
        Quiz createdQuiz = quizRepository.save(newQuiz);

        return new QuizCreateResponse(createdQuiz.getId(), createdQuiz.getTitle());
    }

    @Override
    public QuizCreateResponse createAudioSubjectiveQuiz(AudioSubjectiveQuizCreateRequest request, UserAuthDto auth) {
        Quiz newQuiz = quizFactory.from(request, auth);
        Quiz createdQuiz = quizRepository.save(newQuiz);

        return new QuizCreateResponse(createdQuiz.getId(), createdQuiz.getTitle());
    }

    @Override
    public QuizCreateResponse createBinaryChoiceQuiz(BinaryChoiceQuizCreateRequest request, UserAuthDto auth) {
        Quiz newQuiz = quizFactory.from(request, auth);
        Quiz createdQuiz = quizRepository.save(newQuiz);

        return new QuizCreateResponse(createdQuiz.getId(), createdQuiz.getTitle());
    }

    @Override
    public List<QuizInfo> quizList(int page, int size) {
        return List.of();
    }

    @Override
    public void deleteQuiz(String quizId, UserAuthDto auth) {
        quizRepository.deleteById(quizId);
    }
}
