package com.momori.auth.domain;


import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@RedisHash(value = "refreshToken")
public class RefreshToken {

    @Id
    private String userId;

    @Indexed(unique = true)
    private String token;

    @TimeToLive
    private Long expiration;

}
