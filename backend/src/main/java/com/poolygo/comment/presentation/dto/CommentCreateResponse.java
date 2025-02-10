package com.poolygo.comment.presentation.dto;


import lombok.Getter;

@Getter
public class CommentCreateResponse {
    private final String message;

    private CommentCreateResponse(String message) {
        this.message = message;
    }

    public static CommentCreateResponse success() {
        return new CommentCreateResponse("comment create success");
    }
}
