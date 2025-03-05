package com.momori.global.util;


import com.momori.auth.dto.UserAuthDto;
import com.momori.global.exception.AuthException;
import com.momori.global.exception.ExceptionCode;
import com.momori.global.token.JwtConfiguration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static com.momori.global.config.security.SecurityConstant.*;

@Component
@RequiredArgsConstructor
public class AuthJwtTokenUtil {

    private final JwtConfiguration jwtConfiguration;

    public Optional<UserAuthDto> decodeWithoutIdentifier(String token) {

        try {
            String removeBearer = token.substring(BEARER.length());
            SecretKey key = Keys.hmacShaKeyFor(jwtConfiguration.secretKey().getBytes());

            Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(removeBearer)
                .getPayload();

            Date expiration = claims.getExpiration();
            if (expiration == null || expiration.before(new Date())) {
                throw new AuthException(ExceptionCode.TOKEN_EXPIRATION_END);
            }

            String provider = (String) claims.get(PROVIDER);
            String name = (String) claims.get(NAME);
            String[] roles = ((String) claims.get(ROLE)).split(",");

            return Optional.of(new UserAuthDto(null, provider, name, Arrays.asList(roles)));
        } catch (Exception e) {
            throw new AuthException(ExceptionCode.TOKEN_AUTHENTICATION_FAIL);
        }

    }

    public Optional<UserAuthDto> decode(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtConfiguration.secretKey().getBytes());

            Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

            Date expiration = claims.getExpiration();
            if (expiration == null || expiration.before(new Date())) {
                throw new AuthException(ExceptionCode.TOKEN_EXPIRATION_END);
            }

            String identifier = (String) claims.get(IDENTIFIER);
            String provider = (String) claims.get(PROVIDER);
            String name = (String) claims.get(NAME);
            String[] roles = ((String) claims.get(ROLE)).split(",");

            return Optional.of(new UserAuthDto(identifier, provider, name, Arrays.asList(roles)));
        } catch (Exception e) {
            throw new AuthException(ExceptionCode.TOKEN_AUTHENTICATION_FAIL);
        }
    }

}
