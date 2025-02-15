package com.poolygo.quizdraft.presentation.dto;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 임시 저장 정보의 간단 정보 (제목, 날짜) 조회 요청을 반환할 때 사용되는 DTO
 */
@Getter
public class DraftSimpleResponse {
    private final String draftId;
    private final String quizType;
    private final String title;
    private final LocalDateTime createdAt;

    private DraftSimpleResponse(
        final String draftId,
        final String quizType,
        final String title,
        final LocalDateTime createdAt
    ) {
        this.draftId = draftId;
        this.quizType = quizType;
        this.title = title;
        this.createdAt = createdAt;
    }

    public static DraftSimpleResponse of(
        final String draftId,
        final String quizType,
        final String title,
        final LocalDateTime lastModified
    ) {
        return new DraftSimpleResponse(draftId, quizType, title, lastModified);
    }

}
