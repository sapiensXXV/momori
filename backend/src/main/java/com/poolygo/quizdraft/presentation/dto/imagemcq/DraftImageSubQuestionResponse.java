package com.poolygo.quizdraft.presentation.dto.imagemcq;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DraftImageSubQuestionResponse {
    private final String imageUrl;
    private final List<String> answers;
}