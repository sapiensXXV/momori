package com.momori.quizdraft.presentation;


import com.momori.auth.dto.UserAuthDto;
import com.momori.global.resolver.AuthenticateUser;
import com.momori.quizdraft.application.QuizDraftService;
import com.momori.quizdraft.presentation.dto.audiomcq.DraftAudioMcqDetailResponse;
import com.momori.quizdraft.presentation.dto.audiomcq.DraftAudioMcqQuizRequest;
import com.momori.quizdraft.presentation.dto.imagemcq.DraftImageMcqQuizRequest;
import com.momori.quizdraft.presentation.dto.CreateDraftResponse;
import com.momori.quizdraft.presentation.dto.imagemcq.DraftImageMcqDetailResponse;
import com.momori.quizdraft.presentation.dto.DraftSimpleResponse;
import com.momori.quizdraft.presentation.dto.imgsubjective.DraftImageSubQuizRequest;
import com.momori.quizdraft.presentation.dto.imgsubjective.DraftImageSubQuizDetailResponse;
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
        DraftImageMcqDetailResponse findDraft = quizDraftService.findImageMcqDraft(
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
    public ResponseEntity<DraftImageSubQuizDetailResponse> imageSubDraft(
        @RequestParam("draftId") String draftId,
        @AuthenticateUser UserAuthDto auth
    ) {
        DraftImageSubQuizDetailResponse findDraft = quizDraftService.findImageSubDraft(draftId, auth.getIdentifier(), auth.getProvider());
        return ResponseEntity.ok(findDraft);
    }

    @PostMapping("/draft/audio-mcq")
    public ResponseEntity<CreateDraftResponse> createAudioMcqDraft(
        @RequestBody DraftAudioMcqQuizRequest request,
        @AuthenticateUser UserAuthDto userInfo
    ) {
        String id = quizDraftService.saveOrUpdateDraft(request, userInfo.getIdentifier(), userInfo.getProvider());
        return ResponseEntity.ok(CreateDraftResponse.success(id));
    }

    @GetMapping("/draft/audio-mcq")
    public ResponseEntity<DraftAudioMcqDetailResponse> audioMcqDraft(
        @RequestParam("draftId") String draftId,
        @AuthenticateUser UserAuthDto auth
    ) {
        DraftAudioMcqDetailResponse findDraft = quizDraftService.findAudioMcqDraft(draftId, auth.getIdentifier(), auth.getProvider());
        return ResponseEntity.ok(findDraft);
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
