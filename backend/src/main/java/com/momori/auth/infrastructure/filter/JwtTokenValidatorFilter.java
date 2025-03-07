package com.momori.auth.infrastructure.filter;

import com.momori.auth.application.RefreshTokenService;
import com.momori.global.config.security.SecurityConstant;
import com.momori.global.exception.AuthException;
import com.momori.global.exception.ExceptionCode;
import com.momori.global.token.JwtConfiguration;
import com.momori.global.util.AuthJwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenValidatorFilter extends OncePerRequestFilter {

    private final JwtConfiguration jwtConfiguration;
    private final RefreshTokenService refreshTokenService;
    private final AuthJwtTokenUtil authJwtTokenUtil;

    @Override
    protected void doFilterInternal(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final FilterChain filterChain
    ) throws ServletException, IOException {
        // jwt 토큰 검증 로직
        String bearerKey = request.getHeader(SecurityConstant.AUTHORIZATION_HEADER);
        if (null != bearerKey) {
            try {
                String jwt = bearerKey.substring(SecurityConstant.BEARER.length());
                SecretKey key = Keys.hmacShaKeyFor(jwtConfiguration.secretKey().getBytes(StandardCharsets.UTF_8));
                Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();

                String identifier = (String) claims.get("identifier");
                String role = (String) claims.get("role");

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    identifier,
                    null,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(role)
                );

                SecurityContextHolder.getContext().setAuthentication(auth);
                filterChain.doFilter(request, response);
            } catch (ExpiredJwtException e) {
                Cookie refreshTokenCookie = Arrays.stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals("refreshToken"))
                    .findFirst()
                    .orElseThrow(() -> new AuthException(ExceptionCode.TOKEN_EXPIRED));

                String refreshToken = refreshTokenCookie.getValue();

                Claims claims = e.getClaims();
                String identifier = claims.get("identifier", String.class);
                String provider = claims.get("provider", String.class);

                String savedRefreshToken = refreshTokenService.getRefreshToken(identifier, provider);

                if (refreshToken.equals(savedRefreshToken)) {
                    // JWT 새로운 JWT 토큰 생성
                    String newAuthJwtToken = authJwtTokenUtil.createAuthToken(
                        identifier,
                        claims.get("name", String.class),
                        provider,
                        claims.get("role", String.class),
                        new Date(),
                        Date.from(Instant.now().plus(1, ChronoUnit.HOURS))
                    );

                    String newRefreshToken = refreshTokenService.createRefreshToken();
                    refreshTokenService.deleteRefreshToken(identifier, provider); // 기존 토큰 삭제
                    refreshTokenService.saveRefreshToken(identifier, provider, newRefreshToken, 3600L * 2); // 새로운 리프레시 토큰 삽입

                    addJwtTokenCookie(response, newAuthJwtToken);
                    addRefreshTokenCookie(response, newRefreshToken);

                    filterChain.doFilter(request, response); // 리프레시 토큰이 일치하는 경우 다음 필터로 이동
                } else {
                    throw new AuthException(ExceptionCode.TOKEN_EXPIRED);
                }
            } catch (Exception e) {
                throw new ServletException(e);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private void addRefreshTokenCookie(HttpServletResponse response, String refreshToken) {
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setSecure(false);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(2 * 60 * 60);
        response.addCookie(refreshTokenCookie);
    }

    private void addJwtTokenCookie(HttpServletResponse response, String jwtToken) {
        Cookie jwtCookie = new Cookie("jwtToken", jwtToken);
//        jwtCookie.setHttpOnly(true); // HttpOnly 속성 설정
        jwtCookie.setSecure(false); // HTTPS 환경에서만 전송 (개발 환경에서 HTTPS가 아니라면 false로 설정)
        jwtCookie.setPath("/"); // 쿠키가 유효한 경로 설정
        jwtCookie.setMaxAge(60 * 60); // 쿠키 만료 시간 설정 (단위: 초)
        response.addCookie(jwtCookie);
    }
}
