package com.momori.auth.application;

import com.momori.auth.UserPrincipal;
import com.momori.auth.domain.repository.RefreshTokenRepository;
import com.momori.auth.userinfo.OAuth2UserInfo;
import com.momori.auth.userinfo.OAuth2UserInfoFactory;
import com.momori.global.util.RandomNameGenerator;
import com.momori.user.domain.ProviderInfo;
import com.momori.user.domain.Role;
import com.momori.user.domain.User;
import com.momori.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RandomNameGenerator randomNameGenerator;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String userNameAttributeName = userRequest.getClientRegistration()
            .getProviderDetails()
            .getUserInfoEndpoint()
            .getUserNameAttributeName();

        String providerCode = userRequest.getClientRegistration().getRegistrationId();
        ProviderInfo providerInfo = ProviderInfo.from(providerCode);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerInfo, attributes);
        String userIdentifier = oAuth2UserInfo.getOAuth2Id();

        User user = getUser(userIdentifier, providerInfo);
        return new UserPrincipal(user, attributes, userNameAttributeName);
    }

    private User getUser(String userIdentifier, ProviderInfo providerInfo) {
        Optional<User> optionalUser = userRepository.findByOAuthInfo(userIdentifier, providerInfo);
        if (optionalUser.isEmpty()) {
            // 신규 유저 가입(ROLE_USER)
            String name = randomNameGenerator.generateName();
            User newUser = User.builder()
                .identifier(userIdentifier)
                .name(name)
                .role(Role.ROLE_USER)
                .provider(providerInfo)
                .build();

            return userRepository.save(newUser);
        }
        return optionalUser.get();
    }
}
