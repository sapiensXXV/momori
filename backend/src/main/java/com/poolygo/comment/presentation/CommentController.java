package com.poolygo.comment.presentation;


import com.poolygo.auth.dto.UserAuthDto;
import com.poolygo.comment.application.CommentService;
import com.poolygo.comment.presentation.dto.CommentCreateRequest;
import com.poolygo.comment.presentation.dto.CommentCreateResponse;
import com.poolygo.comment.presentation.dto.CommentDetailResponse;
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
        @PathVariable("quizId") String quizId
    ) {
        log.debug("퀴즈=[{}] 댓글 조회 요청", quizId);
        List<CommentDetailResponse> result = commentService.findComments(quizId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/comment/{quizId}")
    public ResponseEntity<CommentCreateResponse> createComment(
        @PathVariable("quizId") String quizId,
        @RequestBody CommentCreateRequest request,
        @AuthenticateUser UserAuthDto auth
    ) {
        log.debug("퀴즈=[{}] 댓글 작성 요청", quizId);
        //TODO: 댓글 작성 로직 작성. request 에서 authorization 헤더있는지 확인 후
        // - 있다면 로그인한 사용자의 요청으로, 그렇지 않다면 익명의 사용자가 아이디와 패스워드를 주었을 것으로 간주
        CommentCreateResponse response;
        if (auth == null) {
            // 사용자 정보가 없을 경우 익명 댓글 생성
            response = commentService.createAnonymousComment(request);
        } else {
            // 사용자 정보가 전달되었을 경우 회원 댓글 생성
            response = commentService.createUserComment(request, auth);
        }

        return ResponseEntity.ok(response);
    }

}
