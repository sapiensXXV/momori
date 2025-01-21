package com.poolygo;

import com.poolygo.global.token.JwtConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfiguration.class)
@EnableJpaAuditing
public class PoolygoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoolygoApplication.class, args);
    }

}
