package com.poolygo.auth.util;


import com.poolygo.auth.dto.UserAuthInfo;
import com.poolygo.global.token.JwtConfiguration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Optional;

import static com.poolygo.global.config.security.SecurityConstant.*;

@Component
@RequiredArgsConstructor
public class AuthJwtTokenUtil {

    private final JwtConfiguration jwtConfiguration;

    public Optional<UserAuthInfo> decodeWithoutIdentifier(String token) {

        try {
            String removeBearer = token.substring(BEARER.length());
            SecretKey key = Keys.hmacShaKeyFor(jwtConfiguration.secretKey().getBytes());

            Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(removeBearer)
                .getPayload();

            String provider = (String) claims.get(PROVIDER);
            String name = (String) claims.get(NAME);
            String[] roles = ((String) claims.get(ROLE)).split(",");

            return Optional.of(new UserAuthInfo(null, provider, name, Arrays.asList(roles)));
        } catch (Exception e) {
            throw new JwtException("토큰을 해석하는데 문제가 발생했습니다.", e);
        }

    }

}
