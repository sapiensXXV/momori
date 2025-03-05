package com.momori.comment.domain.mapper;


import com.momori.comment.domain.Comment;
import com.momori.comment.domain.CommentType;
import com.momori.comment.domain.dto.CommentDetailRepositoryResponse;
import com.momori.comment.presentation.dto.CommentCreateResponse;
import com.momori.comment.presentation.dto.CommentDetailResponse;
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
