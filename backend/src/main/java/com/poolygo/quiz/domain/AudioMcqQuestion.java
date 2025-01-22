package com.poolygo.quiz.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AudioMcqQuestion {
    private String audioUrl;
    private List<Integer> answers;
}
