package com.poolygo.quizdraft.presentation.dto;

public interface CreateDraftRequest {

    String getTitle();

    String getThumbnailUrl();

    String getType();

    String getDescription();

    String getFormerDraftId();

}
