package com.poolygo.auth.userinfo;

import java.util.Map;

public class DiscordOAuth2User extends OAuth2UserInfo {

    public DiscordOAuth2User(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getOAuth2Id() {
        return (String) attributes.get("id");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }
}

