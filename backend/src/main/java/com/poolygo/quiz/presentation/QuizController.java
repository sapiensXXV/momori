package com.poolygo.quiz.presentation;

import com.poolygo.auth.dto.UserAuthDto;
import com.poolygo.global.resolver.DomainResolver;
import com.poolygo.quiz.presentation.dto.request.quiz.ImageMcqQuizCreateRequest;
import com.poolygo.quiz.presentation.dto.response.QuizCreateResponse;
import com.poolygo.global.resolver.AuthenticateUser;
import com.poolygo.quiz.application.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
@Slf4j
public class QuizController {

    private final QuizService quizService;
    private final DomainResolver domainResolver;

    @PostMapping("/mcq-img")
    public ResponseEntity<QuizCreateResponse> createImageMcqQuiz(
        @RequestBody ImageMcqQuizCreateRequest createRequest,
        @AuthenticateUser UserAuthDto auth
    ) {
        QuizCreateResponse createResponse = quizService.createImageMcqQuiz(createRequest, auth);
        URI quizUri = UriComponentsBuilder
            .fromUriString(domainResolver.baseUrl() + "/quiz/" + createResponse.getQuizId())
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


}
