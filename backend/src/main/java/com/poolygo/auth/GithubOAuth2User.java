package com.poolygo.auth;

import org.springframework.security.oauth2.core.OAuth2AccessToken;

import java.util.Map;

public class GithubOAuth2User extends OAuth2UserInfo {

    public GithubOAuth2User(Map<String, Object> attributes) {
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
