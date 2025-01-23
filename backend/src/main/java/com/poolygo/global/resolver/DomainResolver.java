package com.poolygo.global.resolver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public record DomainResolver(@Value("${domain.base-url}") String baseUrl) {
}
