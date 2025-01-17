package com.poolygo.auth;

import com.poolygo.user.domain.Role;
import com.poolygo.user.domain.User;
import com.poolygo.user.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
public final class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final String SIGNUP_URL;
    private final String AUTH_URL;
    private final UserRepository userRepository;

    public OAuth2SuccessHandler(
        @Value("${url.base.dev}") String BASE_URL,
        @Value("${url.path.signup}") String SIGNUP_URL,
        @Value("${url.path.auth}") String AUTH_URL,
        UserRepository userRepository
    ) {
        this.userRepository = userRepository;
        this.SIGNUP_URL = BASE_URL + SIGNUP_URL;
        this.AUTH_URL = BASE_URL + AUTH_URL;
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
        getRedirectStrategy().sendRedirect(request, response, redirectUri);
    }

    private String getRedirectUriByRole(Role role, String identifier) {
        if (role == Role.ROLE_GUEST) {
            return UriComponentsBuilder.fromUriString(SIGNUP_URL)
                .queryParam("identifier", identifier)
                .build()
                .toUriString();
        }

        return UriComponentsBuilder.fromUriString(AUTH_URL)
            .queryParam("identifier", identifier)
            .build()
            .toUriString();
    }
}
