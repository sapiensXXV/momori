package com.poolygo;

import com.poolygo.global.infrastructure.aws.AwsProperties;
import com.poolygo.global.resolver.DomainConfiguration;
import com.poolygo.global.token.JwtConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableConfigurationProperties({
    JwtConfiguration.class,
    AwsProperties.class,
    DomainConfiguration.class
})
@EnableJpaAuditing
@EnableMongoAuditing
public class PoolygoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoolygoApplication.class, args);

    }

}
