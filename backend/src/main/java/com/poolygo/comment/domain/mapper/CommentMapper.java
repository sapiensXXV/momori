package com.poolygo.comment.domain.mapper;


import com.poolygo.comment.domain.Comment;
import com.poolygo.comment.domain.CommentType;
import com.poolygo.comment.domain.dto.CommentDetailRepositoryResponse;
import com.poolygo.comment.presentation.dto.CommentCreateResponse;
import com.poolygo.comment.presentation.dto.CommentDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "type", qualifiedByName = "mapCommentType")
    CommentDetailResponse toCommentDetailResponse(CommentDetailRepositoryResponse response);

    CommentDetailResponse toCommentDetailResponse(Comment comment);

    CommentCreateResponse toCommentCreateResponse(Comment comment);

    @Named("mapCommentType")
    default String mapCommentType(CommentType type) {
        return type != null ? type.name() : null;
    }
}
