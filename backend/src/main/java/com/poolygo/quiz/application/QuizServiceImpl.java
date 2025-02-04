package com.poolygo.quiz.application;

import com.poolygo.auth.dto.UserAuthDto;
import com.poolygo.quiz.domain.Quiz;
import com.poolygo.quiz.domain.factory.QuizFactory;
import com.poolygo.quiz.infrastructure.QuizRepository;
import com.poolygo.quiz.presentation.dto.QuizInfo;
import com.poolygo.quiz.presentation.dto.request.question.ImageMcqQuestionCreateRequest;
import com.poolygo.quiz.presentation.dto.request.quiz.*;
import com.poolygo.quiz.presentation.dto.response.QuizCreateResponse;
import com.poolygo.s3.S3ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuizFactory quizFactory;
    private final S3ImageService s3ImageService;

    @Override
    public QuizCreateResponse createImageMcqQuiz(ImageMcqQuizCreateRequest request, UserAuthDto auth) {

        //TODO: thumbnailUrl, request.questions.imageUrl 은 draft 객체 키를 가지고 있다.
        //TODO: draft/ -> permanent/ 경로로 이미지를 복사하고, draft/ 키를 가지는 객체는 삭제해야한다.
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
            request.getThumbnailUrl(),
            request.getDescription(),
            request.getType(),
            newQuestions
        );

        //ImageMcqQuizCreateRequest 객체를 새롭게 만들어야한다.
        Quiz newQuiz = quizFactory.from(newRequest, auth);
        Quiz createdQuiz = quizRepository.save(newQuiz);

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
