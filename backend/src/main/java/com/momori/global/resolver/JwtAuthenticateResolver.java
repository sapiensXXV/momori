package com.momori.global.resolver;

import com.momori.auth.dto.UserAuthDto;
import com.momori.global.util.AuthJwtTokenUtil;
import com.momori.global.config.security.SecurityConstant;
import com.momori.global.exception.AuthException;
import com.momori.global.exception.ExceptionCode;
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
    public UserAuthDto resolveArgument(
        final MethodParameter parameter,
        final ModelAndViewContainer mavContainer,
        final NativeWebRequest webRequest,
        final WebDataBinderFactory binderFactory
    ) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String token = extractToken(request);
        if (token == null) return null;

        // TODO: 토큰이 만료된 경우 리프레시 토큰을 확인. 리프레시 토큰이 존재한다면 새로운 만료시간을 가지는 jwt 토큰 발급
        UserAuthDto auth = jwtTokenUtil.decode(token)
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
