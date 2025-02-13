package com.poolygo.quiz.application;

import com.poolygo.auth.dto.UserAuthDto;
import com.poolygo.global.exception.ExceptionCode;
import com.poolygo.global.exception.QuizException;
import com.poolygo.quiz.application.factory.QuizMappingStrategyFactory;
import com.poolygo.quiz.application.strategy.QuizMappingStrategy;
import com.poolygo.quiz.domain.Question;
import com.poolygo.quiz.domain.Quiz;
import com.poolygo.quiz.domain.factory.QuizFactory;
import com.poolygo.quiz.domain.repository.QuizRepository;
import com.poolygo.quiz.presentation.dto.request.question.ImageMcqQuestionCreateRequest;
import com.poolygo.quiz.presentation.dto.request.quiz.*;
import com.poolygo.quiz.presentation.dto.response.QuizCreateResponse;
import com.poolygo.quiz.presentation.dto.response.detail.QuizDetailResponse;
import com.poolygo.quiz.presentation.dto.response.summary.QuizSummaryResponse;
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

import static com.poolygo.quiz.application.QuizSearchType.LATEST;
import static com.poolygo.quiz.application.QuizSearchType.POPULAR;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuizDraftRepository draftRepository;
    private final QuizFactory quizFactory;
    private final QuizMappingStrategyFactory quizMappingStrategyFactory;
    private final S3ImageService s3ImageService;

    @Override
    public QuizCreateResponse createImageMcqQuiz(ImageMcqQuizCreateRequest request, UserAuthDto auth) {

        List<ImageMcqQuestionCreateRequest> questions = request.getQuestions();
        List<ImageMcqQuestionCreateRequest> newQuestions = questions.stream()
            .map(question -> {
                String permanentUrl = s3ImageService.copyDraftToPermanent(question.getImageUrl());
                s3ImageService.deleteObject(question.getImageUrl()); // 임시 객체 삭제
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
    public List<QuizSummaryResponse> quizList(int page, int size, String type, String searchTerm) {

        QuizSearchType quizType = QuizSearchType.from(type);
        Sort sort;
        if (POPULAR.equals(quizType)) {
            sort = Sort.by(Sort.Direction.DESC, "tries");
        } else if (LATEST.equals(quizType)) {
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

        if (StringUtils.hasText(searchTerm)) {
            String regex = ".*" + searchTerm + ".*";
            return quizRepository.findByTitleMatching(regex, pageable).stream()
                .map(q -> new QuizSummaryResponse(q.getId(), q.getThumbnailUrl(), q.getTitle(), q.getDescription()))
                .toList();
        }

        return quizRepository.findAll(pageable)
            .stream()
            .map(q -> new QuizSummaryResponse(q.getId(), q.getThumbnailUrl(), q.getTitle(), q.getDescription()))
            .toList();
    }

    @Override
    public QuizDetailResponse findById(String id) {
        Quiz quiz = quizRepository.findById(id)
            .orElseThrow(() -> new QuizException(ExceptionCode.INVALID_QUIZ_ID));

        quiz.addView();
        QuizMappingStrategy strategy = quizMappingStrategyFactory.getStrategy(quiz.getType());
        return strategy.map(quiz);
    }

    @Override
    public void deleteQuiz(String quizId, UserAuthDto auth) {
        quizRepository.deleteById(quizId);
    }

    @Override
    public void recordResult(QuizResultRequest request) {
        Quiz findQuiz = quizRepository.findById(request.getQuizId())
            .orElseThrow(() -> new QuizException(ExceptionCode.INVALID_QUIZ_ID));

        findQuiz.addTries(); // 퀴즈 시도 횟수 증가

        List<QuizResultRequest.QuestionResultRequest> requestQuestions = request.getQuestions();
        List<? extends Question> questions = findQuiz.getQuestions();

        for (QuizResultRequest.QuestionResultRequest reqQuestion : requestQuestions) {
            // 해당 questionId를 가진 Question 을 찾습니다.
            Question matchedQuestion = questions.stream()
                .filter(q -> q.getQuestionId().equals(reqQuestion.getQuestionId()))
                .findFirst()
                .orElseThrow(() -> new QuizException(ExceptionCode.INVALID_QUESTONI_ID));

            // 시도 횟수를 증가시킵니다.
            matchedQuestion.addTryCount();

            // isCorrect 가 "true" (대소문자 구분 없이)인 경우 정답 횟수를 증가시킵니다.
            if (reqQuestion.isCorrect()) {
                matchedQuestion.addCorrectCount();
            }
        }


    }
}
