package com.poolygo.auth.presentation;


import com.poolygo.auth.application.AuthService;
import com.poolygo.auth.dto.UserAuthDto;
import com.poolygo.auth.presentation.dto.AuthInfoDto;
import com.poolygo.global.config.security.SecurityConstant;
import com.poolygo.global.exception.AuthException;
import com.poolygo.global.exception.ExceptionCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/check")
    public ResponseEntity<AuthInfoDto> authInfo(final HttpServletRequest request) {

        //jwt 토큰을 해석하여 그 안에 담긴 provider, role 정보를 반환
        String bearerToken = request.getHeader(SecurityConstant.AUTHORIZATION_HEADER);
        if (bearerToken == null || bearerToken.isEmpty()) {
            throw new AuthException(ExceptionCode.TOKEN_NOT_FOUND);
        }

        UserAuthDto userAuthDto = authService.decodeJwtWithoutIdentifier(bearerToken);
        AuthInfoDto authInfoDto = new AuthInfoDto(userAuthDto.getProvider(), userAuthDto.getName(), userAuthDto.getRoles());

        return ResponseEntity.ok(authInfoDto);
    }

}
