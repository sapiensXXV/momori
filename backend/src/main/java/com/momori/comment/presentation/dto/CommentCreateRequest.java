package com.momori.comment.presentation.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentCreateRequest {

    private final String name;
    private final String password;
    private final String content;

}
