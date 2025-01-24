package com.poolygo.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AudioSubjectiveQuestion extends Question {
    private String audioUrl;
    private List<String> answers;
}
