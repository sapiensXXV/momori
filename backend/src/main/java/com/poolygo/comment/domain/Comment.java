package com.poolygo.comment.domain;


import com.poolygo.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

import static com.poolygo.comment.domain.CommentType.ANONYMOUS;
import static com.poolygo.comment.domain.CommentType.USER;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String quizId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true) // 익명댓글에는 유저 정보가 없음
    private User user;

    @Column(nullable = false) // 회원 댓글인 경우, 회원의 이름 정보가 들어간다
    private String name;

    @Column(nullable = true) // 회원 댓글인 경우, 비밀번호 정보가 없다
    private String password;

    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommentType type;

    @Column(name = "ip_address", nullable = false)
    private String ip;

    private Comment(String name, String content, String password, String ip, CommentType type) {
        this.name = name;
        this.content = content;
        this.password = password;
        this.ip = ip;
    }

    public static Comment anonymous(String name, String content, String password, String ip) {
        return new Comment(name, content, password, ip, ANONYMOUS);
    }

    public static Comment user(String name, String content, String ip) {
        return new Comment(name, content, null, ip, USER);
    }

    public void addUser(User user) {
        this.user = user;
        user.getComments().add(this);
    }

}
