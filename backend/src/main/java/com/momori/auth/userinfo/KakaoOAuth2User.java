package com.momori.auth.userinfo;

import java.util.Map;

public class KakaoOAuth2User extends OAuth2UserInfo {

    public KakaoOAuth2User(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getOAuth2Id() {
        return Long.toString( (Long) attributes.get("id") );
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }
}

