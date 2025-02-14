package com.poolygo.quizdraft.presentation;


import com.poolygo.auth.dto.UserAuthDto;
import com.poolygo.global.resolver.AuthenticateUser;
import com.poolygo.quizdraft.application.QuizDraftService;
import com.poolygo.quizdraft.presentation.dto.imagemcq.DraftImageMcqQuizRequest;
import com.poolygo.quizdraft.presentation.dto.CreateDraftResponse;
import com.poolygo.quizdraft.presentation.dto.imagemcq.DraftImageMcqDetailResponse;
import com.poolygo.quizdraft.presentation.dto.DraftSimpleResponse;
import com.poolygo.quizdraft.presentation.dto.imgsubjective.DraftImageSubQuizRequest;
import com.poolygo.quizdraft.presentation.dto.imgsubjective.DraftImageSubResponse;
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
    public ResponseEntity<List<DraftSimpleResponse>> simpleDraftInfo(
        @AuthenticateUser UserAuthDto auth
    ) {
        List<DraftSimpleResponse> findList = quizDraftService.findSimpleByAuth(auth.getIdentifier(), auth.getProvider());
        return ResponseEntity.ok(findList);
    }

    @PostMapping("/draft/image-mcq")
    public ResponseEntity<CreateDraftResponse> createImageMcqDraft(
        @RequestBody DraftImageMcqQuizRequest request,
        @AuthenticateUser UserAuthDto userInfo
        ) {
        String id = quizDraftService.saveOrUpdateDraft(request, userInfo.getIdentifier(), userInfo.getProvider());
        return ResponseEntity.ok(CreateDraftResponse.success(id));
    }

    @GetMapping("/draft/image-mcq")
    public ResponseEntity<DraftImageMcqDetailResponse> imageMcqDraft(
        @RequestParam("draftId") String draftId,
        @AuthenticateUser UserAuthDto userInfo
    ) {
        DraftImageMcqDetailResponse findDraft = quizDraftService.findOneImageMcqDraft(
            draftId,
            userInfo.getIdentifier(),
            userInfo.getProvider()
        );

        return ResponseEntity.ok(findDraft);
    }

    @PostMapping("/draft/image-sub")
    public ResponseEntity<CreateDraftResponse> createImageSubDraft(
        @RequestBody DraftImageSubQuizRequest request,
        @AuthenticateUser UserAuthDto userInfo
    ) {
        String id = quizDraftService.saveOrUpdateDraft(request, userInfo.getIdentifier(), userInfo.getProvider());
        return ResponseEntity.ok(CreateDraftResponse.success(id));
    }

    @GetMapping("/draft/image-sub")
    public ResponseEntity<DraftImageSubResponse> imageSubDraft() {
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

    @PostMapping("/draft/image-binary")
    public ResponseEntity<CreateDraftResponse> draftBinary() {
        return null;
    }
}
