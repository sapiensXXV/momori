package com.momori.global.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor
@Slf4j
class RandomNameGeneratorTest {

    @Autowired
    private RandomNameGenerator randomNameGenerator;

    @Test
    @DisplayName("랜덤한 이름 생성")
    void getRandomName() {
        String name = randomNameGenerator.generateName();
        log.info(name);
        assertThat(name).isNotBlank();
    }

}