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
    @JoinColumn(name = "user_id", nullable = true) // 익명댓글에는 유저 정보가 없음.
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean anonymous;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean isMaker;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "ip_address", nullable = false)
    private String ip;

}
