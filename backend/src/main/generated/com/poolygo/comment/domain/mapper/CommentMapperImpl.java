package com.poolygo.comment.domain.mapper;

import com.poolygo.comment.domain.Comment;
import com.poolygo.comment.presentation.dto.CommentCreateResponse;
import com.poolygo.comment.presentation.dto.CommentDetailResponse;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-11T05:07:37+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.11.1.jar, environment: Java 21.0.4 (Eclipse Adoptium)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentDetailResponse toCommentDetailResponse(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        String id = null;
        String name = null;
        LocalDateTime createdAt = null;
        String content = null;
        boolean isMaker = false;

        if ( comment.getId() != null ) {
            id = String.valueOf( comment.getId() );
        }
        name = comment.getName();
        createdAt = comment.getCreatedAt();
        content = comment.getContent();
        isMaker = comment.isMaker();

        CommentDetailResponse commentDetailResponse = new CommentDetailResponse( id, name, createdAt, content, isMaker );

        return commentDetailResponse;
    }

    @Override
    public CommentCreateResponse toCommentCreateResponse(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentCreateResponse.CommentCreateResponseBuilder commentCreateResponse = CommentCreateResponse.builder();

        commentCreateResponse.id( comment.getId() );
        commentCreateResponse.name( comment.getName() );
        commentCreateResponse.content( comment.getContent() );
        commentCreateResponse.createdAt( comment.getCreatedAt() );

        return commentCreateResponse.build();
    }
}
