package com.poolygo.quiz.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ImageSubjectiveQuestion extends Question {
    private String imageUrl;
    private List<String> answers;
}
