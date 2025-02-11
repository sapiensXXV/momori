package com.poolygo.comment.domain.mapper;


import com.poolygo.comment.domain.Comment;
import com.poolygo.comment.presentation.dto.CommentCreateResponse;
import com.poolygo.comment.presentation.dto.CommentDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentDetailResponse toCommentDetailResponse(Comment comment);

    CommentCreateResponse toCommentCreateResponse(Comment comment);

}
