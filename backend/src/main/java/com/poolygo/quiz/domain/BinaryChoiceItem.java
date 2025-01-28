package com.poolygo.quiz.domain;

import lombok.Getter;

@Getter
public class BinaryChoiceItem {
    private final String imageUrl;
    private final String description;
    private final int count; // 얼마나 선택되었는가를 나타내는 필드

    public BinaryChoiceItem(String imageUrl, String description) {
        this.imageUrl = imageUrl;
        this.description = description;
        this.count = 0;
    }
}
