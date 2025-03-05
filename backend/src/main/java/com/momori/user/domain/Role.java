package com.momori.user.domain;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_GUEST("ROLE_GUEST", "손님"),
    ROLE_USER("ROLE_USER", "유저"),
    ROLE_ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String name;

    Role(String key, String name) {
        this.key = key;
        this.name = name;
    }
}
