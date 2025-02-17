package com.poolygo.quiz.application;


import com.poolygo.auth.dto.UserAuthDto;
import com.poolygo.quiz.presentation.dto.request.quiz.QuizCreateRequest;
import com.poolygo.quiz.presentation.dto.response.QuizCreateResponse;
import com.poolygo.quiz.presentation.dto.response.detail.QuizDetailResponse;
import com.poolygo.quiz.presentation.dto.response.summary.QuizSummaryResponse;
import com.poolygo.quiz.presentation.dto.result.QuizResultRequest;

import java.util.List;

public interface QuizService {


    public QuizCreateResponse createQuiz(QuizCreateRequest request, UserAuthDto auth);

    public List<QuizSummaryResponse> quizList(int page, int size, String type, String searchTerm);

    public QuizDetailResponse findById(String id);

    public void deleteQuiz(String quizId, UserAuthDto auth);

    public void recordResult(QuizResultRequest request);

}
