package com.poolygo.quizdraft.presentation.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 임시 저장 정보의 간단 정보 (제목, 날짜) 조회 요청을 반환할 때 사용되는 DTO
 */
@Getter
public class DraftInfoResponse {
    private final String draftId;
    private final String title;
    private final LocalDateTime lastModified;

    private DraftInfoResponse(
        final String draftId,
        final String title,
        final LocalDateTime lastModified
    ) {
        this.draftId = draftId;
        this.title = title;
        this.lastModified = lastModified;
    }

    public static DraftInfoResponse of(
        final String draftId,
        final String title,
        final LocalDateTime lastModified
    ) {
        return new DraftInfoResponse(draftId, title, lastModified);
    }

}
