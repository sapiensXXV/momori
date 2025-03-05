package com.momori.comment.domain;


import com.momori.user.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private boolean maker;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommentType type;

    @Column(name = "ip_address", nullable = false)
    private String ip;


    public void addUser(User user) {
        this.user = user;
        user.getComments().add(this);
    }

}
