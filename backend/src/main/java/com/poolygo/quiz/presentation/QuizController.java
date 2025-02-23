package com.poolygo.quiz.presentation;

import com.poolygo.auth.dto.UserAuthDto;
import com.poolygo.global.resolver.AuthenticateUser;
import com.poolygo.global.resolver.DomainConfiguration;
import com.poolygo.quiz.application.QuizService;
import com.poolygo.quiz.presentation.dto.request.quiz.AudioMcqQuizCreateRequest;
import com.poolygo.quiz.presentation.dto.request.quiz.AudioSubQuizCreateRequest;
import com.poolygo.quiz.presentation.dto.request.quiz.ImageMcqQuizCreateRequest;
import com.poolygo.quiz.presentation.dto.request.quiz.ImageSubQuizCreateRequest;
import com.poolygo.quiz.presentation.dto.response.QuizCreateResponse;
import com.poolygo.quiz.presentation.dto.response.detail.QuizDetailResponse;
import com.poolygo.quiz.presentation.dto.response.summary.QuizSummaryResponse;
import com.poolygo.quiz.presentation.dto.result.AudioMcqQuizResultRequest;
import com.poolygo.quiz.presentation.dto.result.AudioSubQuizResultRequest;
import com.poolygo.quiz.presentation.dto.result.ImageMcqQuizResultRequest;
import com.poolygo.quiz.presentation.dto.result.ImageSubQuizResultRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
@Slf4j
public class QuizController {

    private final QuizService quizService;
    private final DomainConfiguration domainConfiguration;

    @GetMapping("/list")
    public ResponseEntity<List<QuizSummaryResponse>> quizList(
        @RequestParam("page") int page,
        @RequestParam("size") int size,
        @RequestParam("type") String type,
        @RequestParam("searchTerm") String searchTerm
    ) {
        log.debug("page={}, size={}, type={}", page, size, type);
        List<QuizSummaryResponse> result = quizService.quizList(page, size, type, searchTerm);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<QuizDetailResponse> quizDetail(
        @PathVariable("quizId") String quizId
    ) {
        QuizDetailResponse result = quizService.findById(quizId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/mcq-img")
    public ResponseEntity<QuizCreateResponse> createImageMcqQuiz(
        @RequestBody ImageMcqQuizCreateRequest request,
        @AuthenticateUser UserAuthDto auth
    ) {
        QuizCreateResponse response = quizService.createQuiz(request, auth);
        URI quizUri = UriComponentsBuilder
            .fromUriString(domainConfiguration.baseUrl() + "/quiz/" + response.getQuizId())
            .build()
            .toUri();

        return ResponseEntity
            .created(quizUri)
            .body(response);
    }

    @PostMapping("/sub-img")
    public ResponseEntity<QuizCreateResponse> createImageSubjectiveQuiz(
        @RequestBody ImageSubQuizCreateRequest request,
        @AuthenticateUser UserAuthDto auth
    ) {
        QuizCreateResponse response = quizService.createQuiz(request, auth);
        URI quizUri = UriComponentsBuilder
            .fromUriString(domainConfiguration.baseUrl() + "/quiz/" + response.getQuizId())
            .build()
            .toUri();

        return ResponseEntity
            .created(quizUri)
            .body(response);
    }

    @PostMapping("/mcq-audio")
    public ResponseEntity<QuizCreateResponse> createAudioMcqQuiz(
        @RequestBody AudioMcqQuizCreateRequest request,
        @AuthenticateUser UserAuthDto auth
    ) {
        QuizCreateResponse response = quizService.createQuiz(request, auth);
        URI quizUri = UriComponentsBuilder
            .fromUriString(domainConfiguration.baseUrl() + "/quiz/" + response.getQuizId())
            .build()
            .toUri();

        return ResponseEntity
            .created(quizUri)
            .body(response);
    }


    @PostMapping("/sub-audio")
    public ResponseEntity<QuizCreateResponse> createAudioSubjectiveQuiz(
        @RequestBody AudioSubQuizCreateRequest request,
        @AuthenticateUser UserAuthDto auth
    ) {
        QuizCreateResponse response = quizService.createQuiz(request, auth);
        URI quizUri = UriComponentsBuilder
            .fromUriString(domainConfiguration.baseUrl() + "/quiz/" + response.getQuizId())
            .build()
            .toUri();

        return ResponseEntity
            .created(quizUri)
            .body(response);
    }

    @PostMapping("/bin-img")
    public ResponseEntity<QuizCreateResponse> createImageBinaryQuiz(


    ) {
        // TODO: 월드컵 퀴즈 생성 로직
        return null;
    }

    @PostMapping("/result/img-mcq")
    public ResponseEntity<Void> recordImageMcqQuizResult(
        @RequestBody ImageMcqQuizResultRequest request
    ) {
        quizService.recordResult(request);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/result/img-sub")
    public ResponseEntity<Void> recordImageSubQuizResult(
        @RequestBody ImageSubQuizResultRequest request
    ) {
        quizService.recordResult(request);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/result/audio-mcq")
    public ResponseEntity<Void> recordAudioMcqQuizResult(
        @RequestBody AudioMcqQuizResultRequest request
    ) {
        quizService.recordResult(request);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/result/audio-sub")
    public ResponseEntity<Void> recordAudioSubQuizResult(
        @RequestBody AudioSubQuizResultRequest request
    ) {
        quizService.recordResult(request);
        return ResponseEntity.ok(null);
    }
}
