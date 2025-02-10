package com.poolygo.comment.presentation.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class CommentCreateResponse {
    private final Long id;
    private final String content;
    private final String name;
    private final LocalDateTime createdAt;
}
