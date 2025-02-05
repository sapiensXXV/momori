package com.poolygo.quiz.application;

import com.poolygo.auth.dto.UserAuthDto;
import com.poolygo.quiz.domain.Quiz;
import com.poolygo.quiz.domain.factory.QuizFactory;
import com.poolygo.quiz.infrastructure.QuizRepository;
import com.poolygo.quiz.presentation.dto.request.question.ImageMcqQuestionCreateRequest;
import com.poolygo.quiz.presentation.dto.request.quiz.*;
import com.poolygo.quiz.presentation.dto.request.quiz.QuizListRequest.QuizSearchType;
import com.poolygo.quiz.presentation.dto.response.QuizCreateResponse;
import com.poolygo.quiz.presentation.dto.response.QuizSummaryResponse;
import com.poolygo.quizdraft.infrastructure.QuizDraftRepository;
import com.poolygo.s3.S3ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.poolygo.quiz.presentation.dto.request.quiz.QuizListRequest.QuizSearchType.LATEST;
import static com.poolygo.quiz.presentation.dto.request.quiz.QuizListRequest.QuizSearchType.POPULAR;

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
//                s3ImageService.deleteObject(question.getImageUrl()); // 임시 객체 삭제
                log.info("새로운 URL=[{}], 기존의 URL=[{}]", permanentUrl, question.getImageUrl());
                return new ImageMcqQuestionCreateRequest(
                    permanentUrl,
                    question.getChoices()
                );
            })
            .toList();

        // 썸네일 복사
        String permanentThumbnailUrl = s3ImageService.copyDraftToPermanent(request.getThumbnailUrl());
        s3ImageService.deleteObject(request.getThumbnailUrl()); // 임시객체 삭제
        log.info("썸네일 URL=[{}]", permanentThumbnailUrl);

        ImageMcqQuizCreateRequest newRequest = new ImageMcqQuizCreateRequest(
            request.getTitle(),
            request.getDraftId(),
            permanentThumbnailUrl,
            request.getDescription(),
            request.getType(),
            newQuestions
        );

        Quiz newQuiz = quizFactory.from(newRequest, auth);
        Quiz createdQuiz = quizRepository.save(newQuiz);
        if (StringUtils.hasText(request.getDraftId())) {
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
    public List<QuizSummaryResponse> quizList(int page, int size, QuizSearchType type) {

        Sort sort;
        if (POPULAR.equals(type)) {
            sort = Sort.by(Sort.Direction.DESC, "tries");
        } else if (LATEST.equals(type)) {
            sort = Sort.by(Sort.Direction.DESC, "createdAt");
        } else {
            sort = null;
        }

        Pageable pageable;
        if (sort == null) {
            pageable = PageRequest.of(page, size);
        } else {
            pageable = PageRequest.of(page, size, sort);
        }

        List<QuizSummaryResponse> result = quizRepository.findAll(pageable)
            .stream()
            .map(q -> {
                return new QuizSummaryResponse(q.getId(), q.getThumbnailUrl(), q.getTitle(), q.getDescription());
            })
            .toList();
        return result;
    }

    @Override
    public void deleteQuiz(String quizId, UserAuthDto auth) {
        quizRepository.deleteById(quizId);
    }
}
