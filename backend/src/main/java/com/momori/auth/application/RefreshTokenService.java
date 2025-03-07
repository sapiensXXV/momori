package com.momori.auth.application;


import com.momori.global.util.RefreshTokenUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RefreshTokenService {

    private final RedisTemplate<String, String> redisTemplate;
    private final RefreshTokenUtil refreshTokenUtil;

    private final String REFRESH_TOKEN_PREFIX = "refreshToken:";


    public RefreshTokenService(
        final RedisTemplate<String, String> redisTemplate,
        final RefreshTokenUtil refreshTokenUtil
    ) {
        this.redisTemplate = redisTemplate;
        this.refreshTokenUtil = refreshTokenUtil;
    }

    public void saveRefreshToken(
        final String identifier,
        final String provider,
        final String token,
        final Long expiration
    ) {
        String key = refreshTokenUtil.createRefreshTokenKey(identifier, provider);
        redisTemplate.opsForValue().set(key, token, Duration.ofSeconds(expiration));
    }

    public String getRefreshToken(
        final String identifier,
        final String provider
    ) {
        String key = refreshTokenUtil.createRefreshTokenKey(identifier, provider);
        return redisTemplate.opsForValue().get(key);
    }

}
