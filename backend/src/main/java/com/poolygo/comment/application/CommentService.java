package com.poolygo.comment.application;


import com.poolygo.comment.domain.Comment;
import com.poolygo.comment.domain.mapper.CommentMapper;
import com.poolygo.comment.domain.repository.CommentRepository;
import com.poolygo.comment.presentation.dto.CommentDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public List<CommentDetailResponse> findComments(String quizId) {
        List<Comment> comments = commentRepository.findByQuizId(quizId);

        return comments.stream()
            .map(commentMapper::toCommentDetailResponse)
            .toList();
    }

}
