package com.momori.comment.domain.dto;

import com.momori.comment.domain.CommentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDetailRepositoryResponse {

    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private String content;
    private boolean maker;
    private CommentType type;

}
