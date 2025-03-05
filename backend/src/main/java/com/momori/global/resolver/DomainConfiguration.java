package com.momori.global.resolver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public record DomainConfiguration(@Value("${domain.base-url}") String baseUrl) {
}
