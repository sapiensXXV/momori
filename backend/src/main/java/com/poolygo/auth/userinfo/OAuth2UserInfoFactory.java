package com.poolygo.auth.userinfo;

import com.poolygo.user.domain.ProviderInfo;

import java.util.Map;


public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(
        final ProviderInfo providerInfo,
        final Map<String, Object> attributes
    ) {
        switch(providerInfo) {
            case GITHUB -> {
                return new GithubOAuth2User(attributes);
            }
            case GOOGLE -> {
                return new GoogleOAuth2User(attributes);
            }
            case NAVER -> {
                return new NaverOAuth2User(attributes);
            }
            case KAKAO -> {
                return new KakaoOAuth2User(attributes);
            }
            case DISCORD -> {
                return new DiscordOAuth2User(attributes);
            }
        }
        throw new IllegalArgumentException("Invalid Provider Type");
    }
}
