package com.poolygo.auth.userinfo;

import java.util.Map;

public abstract class OAuth2UserInfo {
    public Map<String, Object> attributes;

    public abstract String getOAuth2Id();

    public abstract String getEmail();
}
