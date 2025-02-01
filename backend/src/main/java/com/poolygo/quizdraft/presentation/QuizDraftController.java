package com.poolygo.quizdraft.presentation;


import com.poolygo.auth.dto.UserAuthDto;
import com.poolygo.global.resolver.AuthenticateUser;
import com.poolygo.quizdraft.application.QuizDraftService;
import com.poolygo.quizdraft.presentation.dto.request.CreateDraftImageMcqQuizRequest;
import com.poolygo.quizdraft.presentation.dto.request.DraftImageMcqRequest;
import com.poolygo.quizdraft.presentation.dto.response.CreateDraftResponse;
import com.poolygo.quizdraft.presentation.dto.response.DraftImageMcqResponse;
import com.poolygo.quizdraft.presentation.dto.response.DraftInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
@Slf4j
public class QuizDraftController {

    private final QuizDraftService quizDraftService;

    @GetMapping("/draft")
    public ResponseEntity<List<DraftInfoResponse>> draftInfo(
        @AuthenticateUser UserAuthDto auth
    ) {
        log.info("불러오기를 시도한 유저 id={}, provider={}", auth.getIdentifier(), auth.getProvider());
        List<DraftInfoResponse> findList = quizDraftService.findAllByAuth(auth.getIdentifier(), auth.getProvider());
        log.info("불러오기 결과={}", findList);
        return ResponseEntity.ok(findList);
    }

    @PostMapping("/draft/image-mcq")
    public ResponseEntity<CreateDraftResponse> createImageMcqDraft(
        @RequestBody CreateDraftImageMcqQuizRequest request,
        @AuthenticateUser UserAuthDto userInfo
        ) {
        String id = quizDraftService.saveOrUpdateDraft(request, userInfo.getIdentifier(), userInfo.getProvider());
        return ResponseEntity.ok(CreateDraftResponse.success(id));
    }

    @GetMapping("/draft/image-mcq")
    public ResponseEntity<DraftImageMcqResponse> imageMcqDraft(
        @RequestBody DraftImageMcqRequest request,
        @AuthenticateUser UserAuthDto userInfo
    ) {
        DraftImageMcqResponse findDraft = quizDraftService.findOneImageMcqDraft(
            request.getDraftId(),
            userInfo.getIdentifier(),
            userInfo.getProvider()
        );

        return ResponseEntity.ok(findDraft);
    }

    @PostMapping("/draft/image-sub")
    public ResponseEntity<CreateDraftResponse> createImageSubDraft() {
        return null;
    }

    @PostMapping("/draft/audio-mcq")
    public ResponseEntity<CreateDraftResponse> draftAudioMcq() {
        return null;
    }

    @PostMapping("/draft/audio-sub")
    public ResponseEntity<CreateDraftResponse> draftAudioSub() {
        return null;
    }

    @PostMapping("/draft/binary")
    public ResponseEntity<CreateDraftResponse> draftBinary() {
        return null;
    }
}
