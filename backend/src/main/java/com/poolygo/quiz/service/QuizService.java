package com.poolygo.quiz.service;


import com.poolygo.auth.dto.UserAuthInfo;
import com.poolygo.quiz.presentation.dto.QuizInfo;
import com.poolygo.quiz.presentation.dto.request.quiz.*;
import com.poolygo.quiz.presentation.dto.response.QuizCreateResponse;

import java.util.List;

public interface QuizService {

    /**
     * @param request 이미지 객관식 퀴즈 생성 요청 DTO
     * @param auth    퀴즈를 생성하고자 하는 사용자 정보
     * @return {@link QuizCreateResponse}: 퀴즈 생성 정보 객체. 퀴즈 생성 메세지, 생성된 퀴즈 ID, 퀴즈 제목을 담고 있습니다.
     */
    public QuizCreateResponse createImageMcqQuiz(ImageMcqQuizCreateRequest request, UserAuthInfo auth);

    /**
     * 이미지-주관식 퀴즈를 생성하고 생성된 퀴즈 정보를 {@link QuizCreateResponse}에 담아 반환합니다.
     * @param request 이미지 주관식 퀴즈 생성요청 DTO
     * @param auth    퀴즈를 생성하고자 하는 사용자 정보
     * @return {@link QuizCreateResponse}: 퀴즈 생성 정보 객체. 퀴즈 생성 메세지, 생성된 퀴즈 ID, 퀴즈 제목을 담고 있습니다.
     */
    public QuizCreateResponse createImageSubjectiveQuiz(ImageSubjectiveQuizCreateRequest request, UserAuthInfo auth);

    /**
     * 오디오-객관식 퀴즈를 생성하고 생성된 퀴즈 정보를 {@link QuizCreateResponse}에 담아 반환합니다.
     * @param request 이미지 주관식 퀴즈 생성요청 DTO
     * @param auth    퀴즈를 생성하고자 하는 사용자 정보
     * @return {@link QuizCreateResponse}: 퀴즈 생성 정보 객체. 퀴즈 생성 메세지, 생성된 퀴즈 ID, 퀴즈 제목을 담고 있습니다.
     */
    public QuizCreateResponse createAudioMcqQuiz(AudioMcqQuizCreateRequest request, UserAuthInfo auth);

    /**
     * 오디오-주관식 퀴즈를 생성하고 생성된 퀴즈 정보를 {@link QuizCreateResponse}에 담아 반환합니다.
     * @param request 이미지 주관식 퀴즈 생성요청 DTO
     * @param auth    퀴즈를 생성하고자 하는 사용자 정보
     * @return {@link QuizCreateResponse}: 퀴즈 생성 정보 객체. 퀴즈 생성 메세지, 생성된 퀴즈 ID, 퀴즈 제목을 담고 있습니다.
     */
    public QuizCreateResponse createAudioSubjectiveQuiz(AudioSubjectiveQuizCreateRequest request, UserAuthInfo auth);

    /**
     * 이지선다형 퀴즈를 생성하고 생성된 퀴즈 정보를 {@link QuizCreateResponse}에 담아 반환합니다.
     * @param request 이미지 주관식 퀴즈 생성요청 DTO
     * @param auth    퀴즈를 생성하고자 하는 사용자 정보
     * @return {@link QuizCreateResponse}: 퀴즈 생성 정보 객체. 퀴즈 생성 메세지, 생성된 퀴즈 ID, 퀴즈 제목을 담고 있습니다.
     */
    public QuizCreateResponse createBinaryChoiceQuiz(BinaryChoiceQuizCreateRequest request, UserAuthInfo auth);

    public List<QuizInfo> quizList(int page, int size);

    public void deleteQuiz(String quizId);

}
