package com.poolygo.comment.domain.factory;


import com.poolygo.comment.domain.Comment;
import com.poolygo.comment.domain.CommentType;
import com.poolygo.comment.presentation.dto.CommentCreateRequest;
import com.poolygo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentFactory {

    public Comment createAnonymousComment(String quizId, final CommentCreateRequest request) {
        String name = request.getName();
        String password = request.getPassword(); // 암호화된 패스워드
        String content = request.getContent();

        //TODO: IP 추가
        return Comment.builder()
            .name(name)
            .quizId(quizId)
            .password(password)
            .content(content)
            .type(CommentType.ANONYMOUS)
            .ip("")
            .build();
    }

    public Comment createUserComment(String quizId, final CommentCreateRequest request, final User user) {
        //TODO: IP 추가
        Comment comment = Comment.builder()
            .name(user.getName())
            .quizId(quizId)
            .content(request.getContent())
            .type(CommentType.USER)
            .ip("")
            .build();
        comment.addUser(user);
        return comment;
    }

}
