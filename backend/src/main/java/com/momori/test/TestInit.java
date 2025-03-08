package com.momori.test;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestInit {

    @Value("${momori.oauth2.github.client-id}")
    private String githubClientId;

    @Value("${momori.oauth2.github.client-secret-id}")
    private String githubClientSecretId;

    @Value("${momori.oauth2.naver.client-id}")
    private String naverClientId;

    @Value("${momori.oauth2.naver.client-secret-id}")
    private String naverClientSecretId;

    @Value("${momori.oauth2.kakao.client-id}")
    private String kakaoClientId;

    @Value("${momori.oauth2.kakao.client-secret-id}")
    private String kakaoClientSecretId;

    @Value("${momori.oauth2.discord.client-id}")
    private String discordClientId;

    @Value("${momori.oauth2.discord.client-secret-id}")
    private String discordClientSecretId;

    @Value("${momori.mysql.master-db-host}")
    private String mysqlDataSourceUrl;

    @Value("${momori.mysql.username}")
    private String mysqlUsername;

    @Value("${momori.mysql.password}")
    private String mysqlPassword;

    @Value("${momori.mongodb.host}")
    private String mongodbHost;

    @Value("${momori.mongodb.database}")
    private String mongodbDatabase;

    @Value("${momori.redis.host}")
    private String redisHost;

    @Value("${momori.redis.port}")
    private String redisPort;

    @Value("${momori.redis.username}")
    private String redisUsername;

    @Value("${momori.redis.password}")
    private String redisPassword;


//    @PostConstruct
    public void init() {
        log.info("githubClientId: {}", githubClientId);
        log.info("githubClientSecretId: {}", githubClientSecretId);
        log.info("naverClientId: {}", naverClientId);
        log.info("naverClientSecretId: {}", naverClientSecretId);
        log.info("kakaoClientId: {}", kakaoClientId);
        log.info("kakaoClientSecretId: {}", kakaoClientSecretId);
        log.info("discordClientId: {}", discordClientId);
        log.info("discordClientSecretId: {}", discordClientSecretId);
        log.info("mysqlDataSourceUrl: {}", mysqlDataSourceUrl);
        log.info("mysqlUsername: {}", mysqlUsername);
        log.info("mysqlPassword: {}", mysqlPassword);
        log.info("mongodbHost: {}", mongodbHost);
        log.info("mongodbDatabase: {}", mongodbDatabase);
        log.info("redisHost: {}", redisHost);
        log.info("redisPort: {}", redisPort);
        log.info("redisUsername: {}", redisUsername);
        log.info("redisPassword: {}", redisPassword);
    }

}
