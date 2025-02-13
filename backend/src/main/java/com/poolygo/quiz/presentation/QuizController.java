package com.poolygo.quiz.presentation;

import com.poolygo.auth.dto.UserAuthDto;
import com.poolygo.global.resolver.AuthenticateUser;
import com.poolygo.global.resolver.DomainConfiguration;
import com.poolygo.quiz.application.QuizService;
import com.poolygo.quiz.presentation.dto.request.quiz.ImageMcqQuizCreateRequest;
import com.poolygo.quiz.presentation.dto.request.quiz.QuizResultRequest;
import com.poolygo.quiz.presentation.dto.response.QuizCreateResponse;
import com.poolygo.quiz.presentation.dto.response.detail.QuizDetailResponse;
import com.poolygo.quiz.presentation.dto.response.summary.QuizSummaryResponse;
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
        @RequestBody ImageMcqQuizCreateRequest createRequest,
        @AuthenticateUser UserAuthDto auth
    ) {
        QuizCreateResponse createResponse = quizService.createImageMcqQuiz(createRequest, auth);
        URI quizUri = UriComponentsBuilder
            .fromUriString(domainConfiguration.baseUrl() + "/quiz/" + createResponse.getQuizId())
            .build()
            .toUri();

        return ResponseEntity
            .created(quizUri)
            .body(createResponse);
    }

    @PostMapping("/mcq-audio")
    public ResponseEntity<QuizCreateResponse> createAudioMcqQuiz() {

        return null;
    }

    @PostMapping("/sub-img")
    public ResponseEntity<QuizCreateResponse> createImageSubjectiveQuiz() {
        return null;
    }

    @PostMapping("/sub-audio")
    public ResponseEntity<QuizCreateResponse> createAudioSubjectiveQuiz() {
        return null;
    }

    @PostMapping("/bin-img")
    public ResponseEntity<QuizCreateResponse> createImageBinaryQuiz() {
        return null;
    }

    @PostMapping("/result")
    public ResponseEntity<Void> recordQuizResult(
        @RequestBody QuizResultRequest request
    ) {
        quizService.recordResult(request);

        return ResponseEntity.ok(null);
    }
}
