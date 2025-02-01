package com.poolygo.quizdraft.presentation.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DraftImageMcqQuestionRequest {

    private String imageUrl;
    private List<DraftImageMcqChoiceRequest> choices;

}
