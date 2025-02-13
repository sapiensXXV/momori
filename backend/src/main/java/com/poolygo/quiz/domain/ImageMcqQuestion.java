package com.poolygo.quiz.domain;

import com.poolygo.quiz.presentation.dto.request.quiz.QuizResultRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder // 추상클래스인 부모 클래스의 필드를 설정하기 위해 사용
@NoArgsConstructor
@AllArgsConstructor
public class ImageMcqQuestion extends Question {
    private String imageUrl;
    private List<TextMcqChoice> choices; // DTO 타입으로 부터

    @Override
    public void reflectQuizResult(QuizResultRequest request) {
        
    }
}
