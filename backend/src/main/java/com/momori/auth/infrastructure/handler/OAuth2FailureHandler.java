package com.momori.auth.infrastructure.handler;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
public class OAuth2FailureHandler extends SimpleUrlAuthenticationFailureHandler {

    // 로그인에 실패한 경우
    // 1. 로그인 화면으로 돌아간다.
    // 2. query string을 통해 error 메세지를 전달한다.

    private final String REDIRECT_URL;
    private final String ERROR_PARAM_PREFIX = "error";

    public OAuth2FailureHandler(@Value("${url.base.dev}") String REDIRECT_URL) {
        this.REDIRECT_URL = REDIRECT_URL;
    }

    @Override
    public void onAuthenticationFailure(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final AuthenticationException exception
    ) throws IOException, ServletException {
        // 리다이렉트할 주소를 생성하고, 쿼리 스트링에 메세지를 추가한다.
        String redirectUrl = UriComponentsBuilder.fromUriString(REDIRECT_URL)
            .queryParam(ERROR_PARAM_PREFIX, exception.getMessage())
            .build()
            .toUriString();

        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
