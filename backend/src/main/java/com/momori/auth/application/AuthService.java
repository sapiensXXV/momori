package com.momori.auth.application;


import com.momori.auth.dto.UserAuthDto;
import com.momori.global.exception.AuthException;
import com.momori.global.exception.ExceptionCode;
import com.momori.global.util.AuthJwtTokenUtil;
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
