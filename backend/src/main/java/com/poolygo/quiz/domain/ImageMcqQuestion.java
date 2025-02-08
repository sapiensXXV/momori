package com.poolygo.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
public class ImageMcqQuestion extends Question {
    private String imageUrl;
    private List<ImageMcqChoice> choices; // DTO 타입으로 부터
}
