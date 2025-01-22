package com.poolygo.quiz.service;

import com.poolygo.quiz.presentation.dto.request.quiz.ImageMcqQuizCreateRequest;
import com.poolygo.quiz.presentation.dto.QuizInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    @Override
    public void createQuiz(ImageMcqQuizCreateRequest createRequest) {

    }

    @Override
    public List<QuizInfo> quizList(int page, int size) {
        return List.of();
    }

    @Override
    public void deleteQuiz(String quizId) {

    }
}
