package com.poolygo.quiz.service;

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
    public QuizCreateResponse createImageMcqQuiz(ImageMcqQuizCreateRequest request) {
        log.info("create quiz with title: {}", request.getTitle());

        Quiz newQuiz = quizFactory.from(request);
        log.info("saving quiz: {}", newQuiz);

        quizRepository.save(newQuiz);
        log.info("Quiz save successfully with ID: {}", newQuiz.getId());

        return new QuizCreateResponse(newQuiz.getId(), newQuiz.getTitle());
    }

    @Override
    public QuizCreateResponse createImageSubjectiveQuiz(ImageSubjectiveQuizCreateRequest request) {
        return null;
    }

    @Override
    public QuizCreateResponse createAudioMcqQuiz(AudioMcqQuizCreateRequest request) {
        return null;
    }

    @Override
    public QuizCreateResponse createAudioSubjectiveQuiz(AudioSubjectiveQuizCreateRequest request) {
        return null;
    }

    @Override
    public QuizCreateResponse createBinaryChoiceQuiz(BinaryChoiceQuizCreateRequest request) {
        return null;
    }

    @Override
    public List<QuizInfo> quizList(int page, int size) {
        return List.of();
    }

    @Override
    public void deleteQuiz(String quizId) {

    }
}
