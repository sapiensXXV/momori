package com.poolygo.comment.presentation.dto;


import lombok.Getter;

@Getter
public class CommentDeleteResponse {

    private String message;

    private CommentDeleteResponse(String message) {
        this.message = message;
    }

    public static CommentDeleteResponse success() {
        return new CommentDeleteResponse("댓글 삭제 성공");
    }

    public static CommentDeleteResponse failure() {
        return new CommentDeleteResponse("댓글 삭제 실패");
    }
}
