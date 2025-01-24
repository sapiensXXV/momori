package com.poolygo.quiz.domain.factory;

import com.poolygo.auth.dto.UserAuthDto;
import com.poolygo.quiz.domain.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class UserInfoFactory {

    public UserInfo from(UserAuthDto auth) {
        return new UserInfo(auth.getIdentifier(), auth.getProvider());
    }
}
