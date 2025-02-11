package com.poolygo.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder // 추상클래스인 부모 클래스의 필드를 설정하기 위해 사용
@AllArgsConstructor
public class ImageMcqQuestion extends Question {
    private String imageUrl;
    private List<ImageMcqChoice> choices; // DTO 타입으로 부터
}
