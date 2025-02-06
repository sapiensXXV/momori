package com.poolygo.quiz.application;


import com.poolygo.auth.dto.UserAuthDto;
import com.poolygo.quiz.presentation.dto.request.quiz.*;
import com.poolygo.quiz.presentation.dto.response.QuizCreateResponse;
import com.poolygo.quiz.presentation.dto.response.summary.QuizSummaryResponse;

import java.util.List;

public interface QuizService {

    /**
     * @param request 이미지 객관식 퀴즈 생성 요청 DTO
     * @param auth    퀴즈를 생성하고자 하는 사용자 정보
     * @return {@link QuizCreateResponse}: 퀴즈 생성 정보 객체. 퀴즈 생성 메세지, 생성된 퀴즈 ID, 퀴즈 제목을 담고 있습니다.
     */
    public QuizCreateResponse createImageMcqQuiz(ImageMcqQuizCreateRequest request, UserAuthDto auth);

    /**
     * 이미지-주관식 퀴즈를 생성하고 생성된 퀴즈 정보를 {@link QuizCreateResponse}에 담아 반환합니다.
     * @param request 이미지 주관식 퀴즈 생성요청 DTO
     * @param auth    퀴즈를 생성하고자 하는 사용자 정보
     * @return {@link QuizCreateResponse}: 퀴즈 생성 정보 객체. 퀴즈 생성 메세지, 생성된 퀴즈 ID, 퀴즈 제목을 담고 있습니다.
     */
    public QuizCreateResponse createImageSubjectiveQuiz(ImageSubjectiveQuizCreateRequest request, UserAuthDto auth);

    /**
     * 오디오-객관식 퀴즈를 생성하고 생성된 퀴즈 정보를 {@link QuizCreateResponse}에 담아 반환합니다.
     * @param request 이미지 주관식 퀴즈 생성요청 DTO
     * @param auth    퀴즈를 생성하고자 하는 사용자 정보
     * @return {@link QuizCreateResponse}: 퀴즈 생성 정보 객체. 퀴즈 생성 메세지, 생성된 퀴즈 ID, 퀴즈 제목을 담고 있습니다.
     */
    public QuizCreateResponse createAudioMcqQuiz(AudioMcqQuizCreateRequest request, UserAuthDto auth);

    /**
     * 오디오-주관식 퀴즈를 생성하고 생성된 퀴즈 정보를 {@link QuizCreateResponse}에 담아 반환합니다.
     * @param request 이미지 주관식 퀴즈 생성요청 DTO
     * @param auth    퀴즈를 생성하고자 하는 사용자 정보
     * @return {@link QuizCreateResponse}: 퀴즈 생성 정보 객체. 퀴즈 생성 메세지, 생성된 퀴즈 ID, 퀴즈 제목을 담고 있습니다.
     */
    public QuizCreateResponse createAudioSubjectiveQuiz(AudioSubjectiveQuizCreateRequest request, UserAuthDto auth);

    /**
     * 이지선다형 퀴즈를 생성하고 생성된 퀴즈 정보를 {@link QuizCreateResponse}에 담아 반환합니다.
     * @param request 이미지 주관식 퀴즈 생성요청 DTO
     * @param auth    퀴즈를 생성하고자 하는 사용자 정보
     * @return {@link QuizCreateResponse}: 퀴즈 생성 정보 객체. 퀴즈 생성 메세지, 생성된 퀴즈 ID, 퀴즈 제목을 담고 있습니다.
     */
    public QuizCreateResponse createBinaryChoiceQuiz(BinaryChoiceQuizCreateRequest request, UserAuthDto auth);

    public List<QuizSummaryResponse> quizList(int page, int size, String type, String searchTerm);

    public void deleteQuiz(String quizId, UserAuthDto auth);

}
