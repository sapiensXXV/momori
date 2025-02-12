package com.poolygo.comment.presentation.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentDeleteRequest {
    private final Long id;
    private final String password;
    private final String type;
}
