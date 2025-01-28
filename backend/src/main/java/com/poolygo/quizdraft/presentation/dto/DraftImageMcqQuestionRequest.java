package com.poolygo.quizdraft.presentation.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DraftImageMcqQuestionRequest {

    private String imageUrl;
    private List<DraftImageMcqChoiceRequest> choices;

}
