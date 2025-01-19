package com.poolygo;

import com.poolygo.global.token.JwtConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfiguration.class)
public class PoolygoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoolygoApplication.class, args);
    }

}
