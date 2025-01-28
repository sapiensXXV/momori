package com.poolygo.quizdraft.presentation.dto;


import lombok.Getter;

@Getter
public class DraftResponse {
    private final String message;

    private DraftResponse(String message) {
        this.message = message;
    }

    public static DraftResponse success() {
        return new DraftResponse("draft create success");
    }

    public static DraftResponse failure() {
        return new DraftResponse("draft create failure");
    }
}
