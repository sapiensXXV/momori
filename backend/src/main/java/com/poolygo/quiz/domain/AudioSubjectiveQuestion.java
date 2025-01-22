package com.poolygo.quiz.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AudioSubjectiveQuestion extends Question {
    private String audioUrl;
    private List<String> answers;
}
