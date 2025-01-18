package com.poolygo.auth.service;


import com.poolygo.auth.dto.UserAuthInfo;
import com.poolygo.auth.util.AuthJwtTokenUtil;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthJwtTokenUtil jwtTokenUtil;

    public UserAuthInfo decodeJwtWithoutIdentifier(String token) {
        Optional<UserAuthInfo> authInfo = jwtTokenUtil.decodeWithoutIdentifier(token);
        if (authInfo.isEmpty()) {
            throw new JwtException("토큰으로부터 유저 정보를 해독하지 못했습니다.");
        }
        return authInfo.get();
    }

}
