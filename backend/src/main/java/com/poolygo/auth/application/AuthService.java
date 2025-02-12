package com.poolygo.auth.application;


import com.poolygo.auth.dto.UserAuthDto;
import com.poolygo.global.exception.AuthException;
import com.poolygo.global.exception.ExceptionCode;
import com.poolygo.global.util.AuthJwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthJwtTokenUtil jwtTokenUtil;

    public UserAuthDto decodeJwtWithoutIdentifier(String token) {
        Optional<UserAuthDto> authInfo = jwtTokenUtil.decodeWithoutIdentifier(token);
        if (authInfo.isEmpty()) {
            throw new AuthException(ExceptionCode.TOKEN_AUTHENTICATION_FAIL);
        }
        return authInfo.get();
    }

}
