package com.poolygo.quizdraft.presentation;


import com.poolygo.quizdraft.application.QuizDraftService;
import com.poolygo.quizdraft.presentation.dto.DraftImageMcqQuizRequest;
import com.poolygo.quizdraft.presentation.dto.DraftResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
@Slf4j
public class QuizDraftController {

    private final QuizDraftService quizDraftService;

    @PostMapping("/draft/image-mcq")
    public ResponseEntity<DraftResponse> draftImageMcq(
        @RequestBody DraftImageMcqQuizRequest request
    ) {
        quizDraftService.createImageMcqDraft(request);
        return ResponseEntity.ok(DraftResponse.success());
    }

    @PostMapping("/draft/image-sub")
    public ResponseEntity<DraftResponse> draftImageSub() {
        return null;
    }

    @PostMapping("/draft/audio-mcq")
    public ResponseEntity<DraftResponse> draftAudioMcq() {
        return null;
    }

    @PostMapping("/draft/audio-sub")
    public ResponseEntity<DraftResponse> draftAudioSub() {
        return null;
    }
}
