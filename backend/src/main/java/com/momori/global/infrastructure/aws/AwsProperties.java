package com.momori.global.infrastructure.aws;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cloud.aws")
public record AwsProperties(
    S3 s3,
    @Value("${cloud.aws.region.static}") String region,
    Credentials credentials
) {
    public record S3(String bucket) {}

    public record Credentials(
        @JsonProperty("access-key") String accessKey,
        @JsonProperty("secret-key") String secretKey
    ) {}


}
