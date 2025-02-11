package com.poolygo.comment.presentation.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentDetailResponse {

    private final Long id;
    private final String name;
    private final LocalDateTime createdAt;
    private final String content;
    private final boolean maker;

}
