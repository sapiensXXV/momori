package com.poolygo.global.config.security;

import com.poolygo.auth.CustomOAuth2UserService;
import com.poolygo.auth.handler.OAuth2FailureHandler;
import com.poolygo.auth.handler.OAuth2SuccessHandler;
import com.poolygo.auth.filter.JwtTokenValidatorFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@Order(1)
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final String[] PERMITTED_ROLES = {"GUEST", "USER", "ADMIN"};

    private final CustomCorsConfigurationSource corsConfigurationSource;
    private final CustomOAuth2UserService customOAuthService;
    private final OAuth2SuccessHandler successHandler;
    private final OAuth2FailureHandler failureHandler;

    //filter
    private final JwtTokenValidatorFilter jwtTokenValidatorFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .cors(corsCustomizer -> {
                corsCustomizer.configurationSource(corsConfigurationSource);
            })
            .csrf(CsrfConfigurer::disable)
            .httpBasic(HttpBasicConfigurer::disable)
            .formLogin(FormLoginConfigurer::disable)
            .addFilterBefore(jwtTokenValidatorFilter, BasicAuthenticationFilter.class)
            .authorizeHttpRequests(request -> {
                request.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                    .requestMatchers("/api/admin").hasRole("ADMIN")
                    .requestMatchers("/api/user").hasAnyRole("ADMIN", "USER")
                    .anyRequest().hasAnyRole(PERMITTED_ROLES);
            })
            .oauth2Login(customConfigurer -> {
                customConfigurer
                    .successHandler(successHandler)
                    .failureHandler(failureHandler)
                    .userInfoEndpoint(endpointConfig -> endpointConfig.userService(customOAuthService));
            });
        return http.build();
    }
}
