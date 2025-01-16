package com.poolygo.auth;

import java.util.Map;

public class NaverOAuth2User extends OAuth2UserInfo {

    public NaverOAuth2User(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getOAuth2Id() {
        return (String) attributes.get("registrationId");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }
}

