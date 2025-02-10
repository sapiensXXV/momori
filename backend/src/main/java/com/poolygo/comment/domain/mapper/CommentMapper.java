package com.poolygo.comment.domain.mapper;


import com.poolygo.comment.domain.Comment;
import com.poolygo.comment.presentation.dto.CommentCreateResponse;
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
    @Mapping(target = "isMaker", source = "maker") // Java Bean 규약에 따라 maker 가 됨.
    CommentDetailResponse toCommentDetailResponse(Comment comment);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "createdAt", source = "createdAt")
    CommentCreateResponse toCommentCreateResponse(Comment comment);

}
