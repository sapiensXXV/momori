package com.momori.test;


import com.momori.quiz.presentation.dto.request.quiz.ImageMcqQuizCreateRequest;
import com.momori.quiz.presentation.dto.response.QuizCreateResponse;
import com.momori.quiz.application.QuizService;
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


    // TODO: 테스트 엔드 포인트 막을 것.
    @PostMapping("/quiz")
    public ResponseEntity<String> createQuizTestEndpoint(
        @RequestBody ImageMcqQuizCreateRequest createRequest
    ) {
        QuizCreateResponse createdQuiz = quizService.createQuiz(createRequest, null);
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
