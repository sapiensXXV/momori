package com.poolygo.quiz.resolver;

import com.poolygo.auth.dto.UserAuthInfo;
import com.poolygo.auth.util.AuthJwtTokenUtil;
import com.poolygo.global.config.security.SecurityConstant;
import com.poolygo.global.exception.AuthException;
import com.poolygo.global.exception.ExceptionCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
public class JwtAuthenticateResolver implements HandlerMethodArgumentResolver {

    private final AuthJwtTokenUtil jwtTokenUtil;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticateUser.class);
    }

    @Override
    public UserAuthInfo resolveArgument(
        final MethodParameter parameter,
        final ModelAndViewContainer mavContainer,
        final NativeWebRequest webRequest,
        final WebDataBinderFactory binderFactory
    ) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String token = extractToken(request);
        if (token == null) return null;

        UserAuthInfo auth = jwtTokenUtil.decode(token)
            .orElseThrow(() -> new AuthException(ExceptionCode.TOKEN_AUTHENTICATION_FAIL));

        return auth;
    }

    private String extractToken(HttpServletRequest request) {
        String bearerJwtToken = request.getHeader(SecurityConstant.AUTHORIZATION_HEADER);
        if (bearerJwtToken == null) {
            return null;
        }
        return bearerJwtToken.substring(SecurityConstant.BEARER.length());
    }
}
