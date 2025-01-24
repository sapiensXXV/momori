package com.poolygo.test;


import com.poolygo.quiz.presentation.dto.request.quiz.ImageMcqQuizCreateRequest;
import com.poolygo.quiz.presentation.dto.response.QuizCreateResponse;
import com.poolygo.quiz.service.QuizService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final QuizService quizService;

    @GetMapping("/admin")
    public String admin(HttpServletRequest request) {
        log.info("request={}", request);
        return "this is only admin api";
    }

    @GetMapping("/user")
    public String user(HttpServletRequest request) {
        log.info("request={}", request);
        return "this is only user api";
    }

    @GetMapping("/everyone")
    public String everyone(HttpServletRequest request) {
        log.info("request={}", request);
        return "this is for everyone";
    }

    @PostMapping("/quiz")
    public ResponseEntity<String> createQuizTestEndpoint(
        @RequestBody ImageMcqQuizCreateRequest createRequest
    ) {
        QuizCreateResponse createdQuiz = quizService.createImageMcqQuiz(createRequest, null);
        URI createdUri = UriComponentsBuilder
            .fromUriString("http://localhost:8080/quiz/" + createdQuiz.getQuizId())
            .build()
            .toUri();
        log.info("create quiz endpoint: {}", createdUri);
        return ResponseEntity
            .created(createdUri)
            .build();
    }
}
