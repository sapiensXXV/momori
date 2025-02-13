package com.poolygo.quiz.domain.mapper;

import com.poolygo.quiz.domain.*;
import com.poolygo.quiz.presentation.dto.response.detail.ImageMcqQuestionDetailResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
class ImageMcqMapperTest {

    @Autowired
    ImageMcqMapper imageMcqMapper;

    @Test
    @DisplayName("이미지-객관식 DTO 매퍼 테스트")
    void imageMcqQuestionDetailTest() {

        UserInfo userInfo = new UserInfo("user_identifier", "oauth2_provider");

        TextMcqChoice choice = new TextMcqChoice("choice_content", false, 0);
        ImageMcqQuestion question = new ImageMcqQuestion("image_url", List.of(choice, choice, choice)); // 문제 당 선택지 3개
        List<ImageMcqQuestion> questions = List.of(question, question, question, question, question); // 질문 5개

        // 새로운 퀴즈
        Quiz quiz = Quiz.builder()
            .id("quiz_id")
            .createdAt(LocalDateTime.now())
            .lastModifiedAt(LocalDateTime.now())
            .userInfo(userInfo)
            .title("title")
            .description("description")
            .type(QuizType.IMAGE_MCQ)
            .thumbnailUrl("thumbnail_url")
            .views(0).tries(0).likes(0)
            .questions(questions)
            .build();

        ImageMcqQuestionDetailResponse mapperResult = imageMcqMapper.toImageMcqQuestionDetailResponse(question);
        Assertions.assertThat(mapperResult.getImageUrl()).isEqualTo("image_url");
        Assertions.assertThat(mapperResult.getChoices().size()).isEqualTo(question.getChoices().size());
    }

}