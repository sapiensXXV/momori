package com.poolygo.comment.domain.factory;


import com.poolygo.comment.domain.Comment;
import com.poolygo.comment.presentation.dto.CommentCreateRequest;
import com.poolygo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentFactory {

    public Comment createAnonymousComment(final CommentCreateRequest request) {
        String name = request.getName();
        String password = request.getPassword(); // 암호화된 패스워드
        String content = request.getContent();

        //TODO: IP 추가
        return Comment.anonymous(name, content, password, "");
    }

    public Comment createUserComment(final CommentCreateRequest request, final User user) {
        //TODO: IP 추가
        Comment comment = Comment.user(request.getName(), request.getContent(), "");
        comment.addUser(user);
        return comment;
    }

}
