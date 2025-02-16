package com.poolygo.quiz.domain;


import com.poolygo.quiz.presentation.dto.request.quiz.QuizResultRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.TypeAlias;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TypeAlias("question")
public abstract class Question {
    private String questionId;
    private int tryCount;
    private int correctCount;

    /**
     * 문제의 시도 횟수를 증가시키는 메서드
     */
    public void addTryCount() {
        this.tryCount++;
    }

    /**
     * 문제의 정답 횟수를 증가시키는 메서드
     */
    public void addCorrectCount() {
        this.correctCount++;
    }

    /**
     * 퀴즈의 단일 문제 결과를 반영하는 메서드 입니다.
     * 객관식 문제에서는 문제의 정답을 택했다면 정답 횟수({@code correctCount})를 증가시켜야합니다.
     * 또한 선택한 선택지 번호가 요청 데이터로 주어집니다. 해당 선택지의 선택 횟수({@code selectedCount})를 증가시켜야합니다.
     * 주관식 문제에서는 해당 문제의 정답 횟수({@code correctCount})만 증가시키면 됩니다.
     * @param request 퀴즈의 결과를 담은 요청 데이터
     */
    public abstract void reflectQuizResult(QuizResultRequest.QuestionResultRequest request);
}
