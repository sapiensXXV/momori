package com.poolygo.comment.presentation;


import com.poolygo.comment.application.CommentService;
import com.poolygo.comment.presentation.dto.CommentDetailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comment/{quizId")
    public ResponseEntity<List<CommentDetailResponse>> comments(
        @PathVariable("quizId") String quizId
    ) {
        log.debug("퀴즈=[{}] 댓글 작성 요청", quizId);
        List<CommentDetailResponse> result = commentService.findComments(quizId);
        return ResponseEntity.ok(result);
    }

}
