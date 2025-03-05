package com.momori;

import com.momori.global.infrastructure.aws.AwsProperties;
import com.momori.global.resolver.DomainConfiguration;
import com.momori.global.token.JwtConfiguration;
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
public class MomoriApplication {

    public static void main(String[] args) {
        SpringApplication.run(MomoriApplication.class, args);

    }

}
