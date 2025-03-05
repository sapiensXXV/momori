package com.momori.quiz.domain.factory;

import com.momori.auth.dto.UserAuthDto;
import com.momori.quiz.domain.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class UserInfoFactory {

    public UserInfo from(UserAuthDto auth) {
        return new UserInfo(auth.getIdentifier(), auth.getProvider());
    }

    public UserInfo from(String identifier, String provider) {
        return new UserInfo(identifier, provider);
    }
}
