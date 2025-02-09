package com.poolygo.comment.domain;


import com.poolygo.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true) // 익명댓글에는 유저 정보가 없음
    private User user;

    @Column(nullable = false) // 회원 댓글인 경우, 회원의 이름 정보가 들어간다
    private String name;

    @Column(nullable = true) // 회원 댓글인 경우, 비밀번호 정보가 없다
    private String password;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean isMaker;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "ip_address", nullable = false)
    private String ip;

}
