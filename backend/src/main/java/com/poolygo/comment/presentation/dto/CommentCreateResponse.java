package com.poolygo.comment.presentation.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateResponse {
    private Long id;
    private String content;
    private String name;
    private LocalDateTime createdAt;
    private boolean maker;
}
