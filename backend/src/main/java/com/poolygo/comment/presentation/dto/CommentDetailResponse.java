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
public class CommentDetailResponse {

    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private String content;
    private boolean maker;

}
