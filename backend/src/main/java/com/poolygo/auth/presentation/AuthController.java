package com.poolygo.auth.presentation;


import com.poolygo.auth.dto.UserAuthInfo;
import com.poolygo.auth.presentation.dto.AuthInfoDto;
import com.poolygo.auth.service.AuthService;
import com.poolygo.global.config.security.SecurityConstant;
import com.poolygo.global.exception.NoJwtTokenException;
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
            throw new NoJwtTokenException("[ERROR] 인증 토큰을 찾을 수 없습니다.");
        }

        UserAuthInfo userAuthInfo = authService.decodeJwtWithoutIdentifier(bearerToken);
        AuthInfoDto authInfoDto = new AuthInfoDto(userAuthInfo.getProvider(), userAuthInfo.getRoles());

        return ResponseEntity.ok(authInfoDto);
    }

}
