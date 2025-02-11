package com.poolygo.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1204827576L;

    public static final QUser user = new QUser("user");

    public final ListPath<com.poolygo.comment.domain.Comment, com.poolygo.comment.domain.QComment> comments = this.<com.poolygo.comment.domain.Comment, com.poolygo.comment.domain.QComment>createList("comments", com.poolygo.comment.domain.Comment.class, com.poolygo.comment.domain.QComment.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath identifier = createString("identifier");

    public final StringPath name = createString("name");

    public final EnumPath<ProviderInfo> provider = createEnum("provider", ProviderInfo.class);

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

