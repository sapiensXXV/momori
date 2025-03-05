package com.momori.global.infrastructure.aws;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("local")
class AwsPropertiesTest {

    @Autowired
    AwsProperties awsProperties;

    @Test
    @DisplayName("AWS 관련 설정정보가 매핑되었는지 확인")
    void propertiesMappingTest() {
        assertThat(awsProperties).isNotNull();
        assertThat(awsProperties.s3().bucket()).isNotNull();
        assertThat(awsProperties.credentials().accessKey()).isNotNull();
        assertThat(awsProperties.credentials().secretKey()).isNotNull();
    }

}