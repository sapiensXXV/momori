package com.poolygo.user.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ProviderInfo {

    GITHUB(null, "id", "login"),
    KAKAO("kakao_account", "id", "email"),
    NAVER("response", "id", "email"),
    GOOGLE(null, "sub", "email");

    private final String attributeKey;
    private final String providerCode;
    private final String identifier;

    public static ProviderInfo from(String provider) {
        String providerName = provider.toUpperCase();
        return Arrays.stream(ProviderInfo.values())
            .filter(item -> item.name().equals(providerName))
            .findFirst()
            .orElseThrow();
    }
}
