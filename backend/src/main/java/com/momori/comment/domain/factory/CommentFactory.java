package com.momori.comment.domain.factory;


import com.momori.comment.domain.Comment;
import com.momori.comment.domain.CommentType;
import com.momori.comment.presentation.dto.CommentCreateRequest;
import com.momori.user.domain.User;
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
            .maker(false) // 익명 유저는 퀴즈를 만들 수 없다.
            .ip("")
            .build();
    }

    public Comment createUserComment(
        String quizId,
        final CommentCreateRequest request,
        final User user,
        final boolean isMaker
    ) {
        //TODO: IP 추가
        Comment comment = Comment.builder()
            .name(user.getName())
            .quizId(quizId)
            .content(request.getContent())
            .type(CommentType.USER)
            .maker(isMaker)
            .ip("")
            .build();
        comment.addUser(user);
        return comment;
    }

}
