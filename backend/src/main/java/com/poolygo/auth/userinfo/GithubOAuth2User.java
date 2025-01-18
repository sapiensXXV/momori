package com.poolygo.auth.userinfo;

import java.util.Map;

public class GithubOAuth2User extends OAuth2UserInfo {

    public GithubOAuth2User(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getOAuth2Id() {
        return Integer.toString((Integer) attributes.get("id"));
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }
}
