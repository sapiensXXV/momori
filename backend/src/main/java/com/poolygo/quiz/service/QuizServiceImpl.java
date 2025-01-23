package com.poolygo.quiz.service;

import com.poolygo.auth.dto.UserAuthInfo;
import com.poolygo.quiz.domain.Quiz;
import com.poolygo.quiz.domain.factory.QuizFactory;
import com.poolygo.quiz.domain.repository.QuizRepository;
import com.poolygo.quiz.presentation.dto.QuizInfo;
import com.poolygo.quiz.presentation.dto.request.quiz.*;
import com.poolygo.quiz.presentation.dto.response.QuizCreateResponse;
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

    @Override
    public QuizCreateResponse createImageMcqQuiz(ImageMcqQuizCreateRequest request, UserAuthInfo auth) {
        Quiz newQuiz = quizFactory.from(request, auth);
        Quiz createdQuiz = quizRepository.save(newQuiz);

        return new QuizCreateResponse(createdQuiz.getId(), createdQuiz.getTitle());
    }

    @Override
    public QuizCreateResponse createImageSubjectiveQuiz(ImageSubjectiveQuizCreateRequest request, UserAuthInfo auth) {
        Quiz newQuiz = quizFactory.from(request, auth);
        Quiz createdQuiz = quizRepository.save(newQuiz);

        return new QuizCreateResponse(createdQuiz.getId(), createdQuiz.getTitle());
    }

    @Override
    public QuizCreateResponse createAudioMcqQuiz(AudioMcqQuizCreateRequest request, UserAuthInfo auth) {
        Quiz newQuiz = quizFactory.from(request, auth);
        Quiz createdQuiz = quizRepository.save(newQuiz);

        return new QuizCreateResponse(createdQuiz.getId(), createdQuiz.getTitle());
    }

    @Override
    public QuizCreateResponse createAudioSubjectiveQuiz(AudioSubjectiveQuizCreateRequest request, UserAuthInfo auth) {
        Quiz newQuiz = quizFactory.from(request, auth);
        Quiz createdQuiz = quizRepository.save(newQuiz);

        return new QuizCreateResponse(createdQuiz.getId(), createdQuiz.getTitle());
    }

    @Override
    public QuizCreateResponse createBinaryChoiceQuiz(BinaryChoiceQuizCreateRequest request, UserAuthInfo auth) {
        Quiz newQuiz = quizFactory.from(request, auth);
        Quiz createdQuiz = quizRepository.save(newQuiz);

        return new QuizCreateResponse(createdQuiz.getId(), createdQuiz.getTitle());
    }

    @Override
    public List<QuizInfo> quizList(int page, int size) {
        return List.of();
    }

    @Override
    public void deleteQuiz(String quizId) {
        
    }
}
