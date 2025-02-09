package com.poolygo.comment.domain.mapper;


import com.poolygo.comment.domain.Comment;
import com.poolygo.comment.presentation.dto.CommentDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "isMaker", source = "isMaker")
    CommentDetailResponse toCommentDetailResponse(Comment comment);

}
