package com.poolygo.global.token;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt")
public record JwtConfiguration(String secretKey) {}
