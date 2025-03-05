package com.momori.auth.userinfo;

import java.util.Map;

public class GoogleOAuth2User extends OAuth2UserInfo {

    public GoogleOAuth2User(Map<String, Object> attributes) {
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
