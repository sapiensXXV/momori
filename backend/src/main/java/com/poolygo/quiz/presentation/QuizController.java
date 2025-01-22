package com.poolygo.quiz.presentation;

import com.poolygo.quiz.presentation.dto.request.quiz.ImageMcqQuizCreateRequest;
import com.poolygo.quiz.service.QuizService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/quiz")
    public ResponseEntity<ImageMcqQuizCreateRequest> create(final HttpServletResponse response) {
        return ResponseEntity
            .created(URI.create(""))
            .build();
    }
}
