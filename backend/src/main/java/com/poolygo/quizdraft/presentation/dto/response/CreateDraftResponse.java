package com.poolygo.quizdraft.presentation.dto.response;


import lombok.Getter;

@Getter
public class CreateDraftResponse {
    private final String message;
    private final String draftId;

    private CreateDraftResponse(final String message, final String draftId) {
        this.message = message;
        this.draftId = draftId;
    }

    public static CreateDraftResponse success(final String draftId) {
        return new CreateDraftResponse("draft request success", draftId);
    }

    public static CreateDraftResponse failure() {
        return new CreateDraftResponse("draft create failure", null);
    }
}
