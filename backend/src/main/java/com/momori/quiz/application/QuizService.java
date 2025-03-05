package com.momori.quiz.application;


import com.momori.auth.dto.UserAuthDto;
import com.momori.quiz.presentation.dto.request.quiz.QuizCreateRequest;
import com.momori.quiz.presentation.dto.response.QuizCreateResponse;
import com.momori.quiz.presentation.dto.response.detail.QuizDetailResponse;
import com.momori.quiz.presentation.dto.response.summary.QuizSummaryResponse;
import com.momori.quiz.presentation.dto.result.QuizResultRequest;

import java.util.List;

public interface QuizService {


    public QuizCreateResponse createQuiz(QuizCreateRequest request, UserAuthDto auth);

    public List<QuizSummaryResponse> quizList(int page, int size, String type, String searchTerm);

    public QuizDetailResponse findById(String id);

    public void deleteQuiz(String quizId, UserAuthDto auth);

    public void recordResult(QuizResultRequest request);

}
