package com.poolygo.quiz.presentation;

import com.poolygo.quiz.presentation.dto.request.quiz.ImageMcqQuizCreateRequest;
import com.poolygo.quiz.presentation.dto.response.QuizCreateResponse;
import com.poolygo.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/quiz/mcq/image")
    public ResponseEntity<QuizCreateResponse> createImageMcqQuiz(
        @RequestBody ImageMcqQuizCreateRequest createRequest
    ) {

        quizService.createImageMcqQuiz(createRequest);

        return ResponseEntity
            .created(URI.create(""))
            .build();
    }

    @PostMapping("/quiz/mcq/audio")
    public ResponseEntity<QuizCreateResponse> createAudioMcqQuiz() {
        return null;
    }

    @PostMapping("/quiz/subjective/image")
    public ResponseEntity<QuizCreateResponse> createImageSubjectiveQuiz() {
        return null;
    }

    @PostMapping("/quiz/subjective/audio")
    public ResponseEntity<QuizCreateResponse> createAudioSubjectiveQuiz() {
        return null;
    }

    @PostMapping("/quiz/binary/image")
    public ResponseEntity<QuizCreateResponse> createImageBinaryQuiz() {
        return null;
    }
}
