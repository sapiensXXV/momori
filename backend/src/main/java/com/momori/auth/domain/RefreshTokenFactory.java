package com.momori.auth.domain;


import org.springframework.stereotype.Service;

@Service
public class RefreshTokenFactory {

    public RefreshToken create(
        final String userIdentifier,
        final String token,
        final Long timeToLive
    ) {
        return new RefreshToken(
            userIdentifier,
            token,
            timeToLive
        );
    }

}
