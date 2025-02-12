package com.poolygo.comment.domain.repository;

import com.poolygo.comment.domain.dto.CommentDetailRepositoryResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.poolygo.comment.domain.QComment.comment;


@Repository
@RequiredArgsConstructor
public class CommentQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<CommentDetailRepositoryResponse> commentList(String quizId, long lastId, int size) {
        List<CommentDetailRepositoryResponse> result = queryFactory.select(
                Projections.fields(
                    CommentDetailRepositoryResponse.class,
                    comment.id,
                    comment.name,
                    comment.createdAt,
                    comment.content,
                    comment.maker,
                    comment.type
                )
            )
            .from(comment)
            .where(
                ltCommentId(lastId),
                comment.quizId.eq(quizId)
            )
            .orderBy(comment.id.desc())
            .limit(size)
            .fetch();

        return result;
    }

    /**
     * @param commentId 비교대상이 되는
     * @return {@code commentId} 보다 작은 Comment 엔티티 조건
     */
    private BooleanExpression ltCommentId(Long commentId) {
        if (commentId == null) return null;
        return comment.id.lt(commentId);
    }

}
