package com.poolygo.global.config.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@Component
public class CustomCorsConfigurationSource implements CorsConfigurationSource {

    private final List<String> ALLOWED_ORIGINS;
    private final List<String> ALLOWED_METHODS = List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTION");

    public CustomCorsConfigurationSource(@Value("${url.base.dev}") List<String> origins) {
        this.ALLOWED_ORIGINS = origins;
    }

    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(ALLOWED_ORIGINS);
        config.setAllowedMethods(ALLOWED_METHODS);
        config.setExposedHeaders(Collections.singletonList("Authorization"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setMaxAge(3000L);
        return config;
    }
}
