package com.poolygo.quizdraft.presentation;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
@Slf4j
public class QuizDraftController {

    @PostMapping("/draft/image-mcq")
    public ResponseEntity<?> draftImageMcq() {
        return null;
    }

    @PostMapping("/draft/image-sub")
    public ResponseEntity<?> draftImageSub() {
        return null;
    }

    @PostMapping("/draft/audio-mcq")
    public ResponseEntity<?> draftAudioMcq() {
        return null;
    }

    @PostMapping("/draft/audio-sub")
    public ResponseEntity<?> draftAudioSub() {
        return null;
    }
}
