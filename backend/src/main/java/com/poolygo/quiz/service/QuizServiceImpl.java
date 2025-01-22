package com.poolygo.quiz.service;

import com.poolygo.quiz.domain.Quiz;
import com.poolygo.quiz.domain.factory.QuizFactory;
import com.poolygo.quiz.domain.repository.QuizRepository;
import com.poolygo.quiz.presentation.dto.QuizInfo;
import com.poolygo.quiz.presentation.dto.request.quiz.*;
import com.poolygo.quiz.presentation.dto.response.QuizCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuizFactory quizFactory;

    @Override
    public QuizCreateResponse createImageMcqQuiz(ImageMcqQuizCreateRequest request) {
        Quiz newQuiz = quizFactory.from(request);
        quizRepository.save(newQuiz);

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
