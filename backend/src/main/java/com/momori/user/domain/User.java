package com.momori.user.domain;

import com.momori.comment.domain.Comment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users") // mysql에서 user는 예약어
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProviderInfo provider;

    @NotNull
    private String identifier;

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public User(
        final String identifier,
        final String name,
        final Role role,
        final ProviderInfo provider
    ) {
        this.identifier = identifier;
        this.name = name;
        this.role = role;
        this.provider = provider;
    }
}
