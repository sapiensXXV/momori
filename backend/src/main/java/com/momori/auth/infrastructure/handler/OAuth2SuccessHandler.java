package com.momori.auth.infrastructure.handler;

import com.momori.auth.application.RefreshTokenService;
import com.momori.global.config.security.SecurityConstant;
import com.momori.global.util.AuthJwtTokenUtil;
import com.momori.user.domain.Role;
import com.momori.user.domain.User;
import com.momori.user.repository.UserRepository;
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

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@Slf4j
public final class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

//    private final String SIGNUP_URL;
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;
    private final AuthJwtTokenUtil authJwtTokenUtil;

    @Value("${url.base}") 
    private String BASE_URL;

    @Value("${url.path.signup}") 
    private String SIGNUP_PATH;

    public OAuth2SuccessHandler(
        UserRepository userRepository,
        RefreshTokenService refreshTokenService,
        AuthJwtTokenUtil authJwtTokenUtil
    ) {
        this.userRepository = userRepository;
        this.refreshTokenService = refreshTokenService;
        this.authJwtTokenUtil = authJwtTokenUtil;
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
        String jwtToken = authJwtTokenUtil.createAuthToken(
            user.getIdentifier(),
            user.getName(),
            user.getProvider().name(),
            user.getRole().name(),
            new Date(),
            Date.from(Instant.now().plus(1, ChronoUnit.HOURS))
        );
        response.setHeader(SecurityConstant.AUTHORIZATION_HEADER, SecurityConstant.BEARER + jwtToken);

        // 리프레시 토큰 생성
        String refreshToken = refreshTokenService.createRefreshToken();
        refreshTokenService.saveRefreshToken(identifier, user.getProvider().name(), refreshToken, 3600L);

        // 쿠키 추가
        addJwtTokenCookie(response, jwtToken);
        addRefreshTokenCookie(response, refreshToken);

        getRedirectStrategy().sendRedirect(request, response, redirectUri);
    }

    private void addRefreshTokenCookie(HttpServletResponse response, String refreshToken) {
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setSecure(false);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(60 * 60);
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

    private String getRedirectUriByRole(Role role, String identifier) {
        if (role == Role.ROLE_GUEST) {
            return UriComponentsBuilder.fromUriString(BASE_URL + SIGNUP_PATH)
                .queryParam("identifier", identifier)
                .build()
                .toUriString();
        }

        // 그 외 로그인을 완료한 유저는 콜백으로 리다이렉션
        return BASE_URL + "/auth/callback";
    }
}
