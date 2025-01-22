package com.poolygo.quiz.service;


import com.poolygo.quiz.presentation.dto.request.quiz.ImageMcqQuizCreateRequest;
import com.poolygo.quiz.presentation.dto.QuizInfo;

import java.util.List;

public interface QuizService {

    public void createQuiz(ImageMcqQuizCreateRequest createRequest);

    public List<QuizInfo> quizList(int page, int size);

    public void deleteQuiz(String quizId);

}
