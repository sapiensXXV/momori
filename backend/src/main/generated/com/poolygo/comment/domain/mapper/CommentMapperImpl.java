package com.poolygo.comment.domain.mapper;

import com.poolygo.comment.domain.Comment;
import com.poolygo.comment.presentation.dto.CommentCreateResponse;
import com.poolygo.comment.presentation.dto.CommentDetailResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-12T03:19:24+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.11.1.jar, environment: Java 21.0.4 (Eclipse Adoptium)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentDetailResponse toCommentDetailResponse(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDetailResponse.CommentDetailResponseBuilder commentDetailResponse = CommentDetailResponse.builder();

        commentDetailResponse.id( comment.getId() );
        commentDetailResponse.name( comment.getName() );
        commentDetailResponse.createdAt( comment.getCreatedAt() );
        commentDetailResponse.content( comment.getContent() );
        commentDetailResponse.maker( comment.isMaker() );

        return commentDetailResponse.build();
    }

    @Override
    public CommentCreateResponse toCommentCreateResponse(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentCreateResponse.CommentCreateResponseBuilder commentCreateResponse = CommentCreateResponse.builder();

        commentCreateResponse.id( comment.getId() );
        commentCreateResponse.content( comment.getContent() );
        commentCreateResponse.name( comment.getName() );
        commentCreateResponse.createdAt( comment.getCreatedAt() );

        return commentCreateResponse.build();
    }
}
