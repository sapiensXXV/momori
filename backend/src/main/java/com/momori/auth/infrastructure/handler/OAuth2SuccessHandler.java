package com.momori.auth.infrastructure.handler;

import com.momori.global.config.security.SecurityConstant;
import com.momori.global.token.JwtConfiguration;
import com.momori.user.domain.Role;
import com.momori.user.domain.User;
import com.momori.user.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@Slf4j
public final class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final String SIGNUP_URL;
    private final String AUTH_URL;
    private final UserRepository userRepository;
    private final JwtConfiguration jwtConfiguration;

    public OAuth2SuccessHandler(
        @Value("${url.base.dev}") String BASE_URL,
        @Value("${url.path.signup}") String SIGNUP_URL,
        @Value("${url.path.auth}") String AUTH_URL,
        UserRepository userRepository,
        JwtConfiguration jwtConfiguration
    ) {
        this.userRepository = userRepository;
        this.SIGNUP_URL = BASE_URL + SIGNUP_URL;
        this.AUTH_URL = BASE_URL + AUTH_URL;
        this.jwtConfiguration = jwtConfiguration;
    }

    @Override
    public void onAuthenticationSuccess(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final Authentication authentication
    ) throws IOException, ServletException {
        // 소셜로그인이 성공했을 경우 취해줄 로직을 정의한다.
        // 로그인이 성공했을 경우
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String identifier = oAuth2User.getName();
        User user = userRepository.findByIdentifier(identifier)
            .orElseThrow(() -> new IllegalArgumentException("잘못된 식별자 입니다."));

        String redirectUri = getRedirectUriByRole(user.getRole(), identifier);
        log.info("[{}]로 리다이렉트", redirectUri);
        String jwtToken = createJwtToken(user);
        response.setHeader(SecurityConstant.AUTHORIZATION_HEADER, SecurityConstant.BEARER + jwtToken);

        // TODO: 리프레시 토큰 생성

        // 쿠키 생성 및 HttpOnly 설정
        Cookie jwtCookie = new Cookie("jwtToken", jwtToken);
//        jwtCookie.setHttpOnly(true); // HttpOnly 속성 설정
        jwtCookie.setSecure(false); // HTTPS 환경에서만 전송 (개발 환경에서 HTTPS가 아니라면 false로 설정)
        jwtCookie.setPath("/"); // 쿠키가 유효한 경로 설정
        jwtCookie.setMaxAge(60 * 60); // 쿠키 만료 시간 설정 (단위: 초)

        // 쿠키 추가
        response.addCookie(jwtCookie);

        getRedirectStrategy().sendRedirect(request, response, redirectUri);
    }

    private String getRedirectUriByRole(Role role, String identifier) {
        if (role == Role.ROLE_GUEST) {
            return UriComponentsBuilder.fromUriString(SIGNUP_URL)
                .queryParam("identifier", identifier)
                .build()
                .toUriString();
        }

        // 그 외 로그인을 완료한 유저는 콜백으로 리다이렉션
        return "http://localhost:5173/auth/callback";
    }

    private String createJwtToken(User user) {
        SecretKey key = Keys.hmacShaKeyFor(jwtConfiguration.secretKey().getBytes(StandardCharsets.UTF_8));

        return Jwts.builder().issuer("poolygo").subject("OAuth2 LOGIN TOKEN")
            .claim("identifier", user.getIdentifier())
            .claim("name", user.getName())
            .claim("provider", user.getProvider())
            .claim("role", user.getRole())
            .issuedAt(new Date())
            .expiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
            .signWith(key)
            .compact();
    }

}
