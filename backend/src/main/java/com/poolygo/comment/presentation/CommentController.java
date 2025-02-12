package com.poolygo.comment.presentation;


import com.poolygo.auth.dto.UserAuthDto;
import com.poolygo.comment.application.CommentService;
import com.poolygo.comment.presentation.dto.*;
import com.poolygo.global.resolver.AuthenticateUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comment/{quizId}")
    public ResponseEntity<List<CommentDetailResponse>> comments(
        @PathVariable("quizId") String quizId,
        @RequestParam("lastId") long lastId,
        @RequestParam("size") int size
    ) {
        log.info("퀴즈=[{}] 댓글 조회 요청", quizId);
        List<CommentDetailResponse> result = commentService.findComments(quizId, lastId, size);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/comment/{quizId}")
    public ResponseEntity<CommentCreateResponse> createComment(
        @PathVariable("quizId") String quizId,
        @RequestBody CommentCreateRequest request,
        @AuthenticateUser UserAuthDto auth
    ) {
        log.info("퀴즈=[{}] 댓글 작성 요청", quizId);
        CommentCreateResponse response;
        if (auth == null) {
            // 사용자 정보가 없을 경우 익명 댓글 생성
            response = commentService.createAnonymousComment(quizId, request);
        } else {
            // 사용자 정보가 전달되었을 경우 회원 댓글 생성
            response = commentService.createUserComment(quizId, request, auth);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/comment")
    public ResponseEntity<CommentDeleteResponse> createComment(
        @RequestBody CommentDeleteRequest request,
        @AuthenticateUser UserAuthDto auth
    ) {
        commentService.deleteComment(request, auth);
        return ResponseEntity.ok(CommentDeleteResponse.success());
    }

}
