package com.poolygo.quiz.domain;

import com.poolygo.quiz.presentation.dto.result.ImageMcqQuizResultRequest.ImageMcqQuestionResultRequest;
import com.poolygo.quiz.presentation.dto.result.QuestionResultRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.TypeAlias;

import java.util.List;

@Getter
@SuperBuilder// 추상클래스인 부모 클래스의 필드를 설정하기 위해 사용
@NoArgsConstructor
@AllArgsConstructor
@TypeAlias("imageMcqQuestion")
public class ImageMcqQuestion extends Question {
    private String imageUrl;
    private List<TextMcqChoice> choices; // DTO 타입으로 부터

    @Override
    public void reflectQuizResult(QuestionResultRequest request) {

        ImageMcqQuestionResultRequest result = (ImageMcqQuestionResultRequest) request;

        this.addTryCount(); // 시도 카운트 증가
        if (result.isCorrect()) {
            this.addCorrectCount(); // 정답 카운트 증가
        }
        List<Integer> selectedChoices = result.getChoices();
        try {
            // 선택지 선택 카운트 증가.
            selectedChoices.forEach((index) -> choices.get(index).addSelectedCount());
        } catch (Exception e) {
            throw new IllegalArgumentException("결과를 반영하던 중 문제가 발생하였습니다.");
        }
    }
}
