package com.momori.comment.domain.mapper;

import com.momori.comment.domain.Comment;
import com.momori.comment.presentation.dto.CommentDetailResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class CommentMapperTest {

    @Autowired
    CommentMapper commentMapper;

    @Test
    @DisplayName("댓글 DTO 매퍼 테스트")
    void commentCreateDtoMapperTest() {
        Comment newComment = Comment.builder()
            .name("name")
            .content("content")
            .createdAt(LocalDateTime.now())
            .build();
        CommentDetailResponse response = commentMapper.toCommentDetailResponse(newComment);
        assertThat(response.getContent()).isEqualTo("content");
        assertThat(response.getName()).isEqualTo(newComment.getName());
    }

}