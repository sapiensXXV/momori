package com.poolygo.quizdraft.presentation.dto;


import lombok.Getter;

@Getter
public class DraftResponse {
    private final String message;
    private final String draftId;

    private DraftResponse(final String message, final String draftId) {
        this.message = message;
        this.draftId = draftId;
    }

    public static DraftResponse success(final String draftId) {
        return new DraftResponse("draft create success", draftId);
    }

    public static DraftResponse failure() {
        return new DraftResponse("draft create failure", null);
    }
}
