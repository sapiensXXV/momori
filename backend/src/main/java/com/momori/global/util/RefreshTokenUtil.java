package com.momori.global.util;


import org.springframework.stereotype.Component;

@Component
public class RefreshTokenUtil {

    private final String REFRESH_TOKEN_PREFIX = "refreshToken:";

    public String createRefreshTokenKey(final String identifier, final String provider) {
        return REFRESH_TOKEN_PREFIX + provider + "_" + identifier;
    }

}
