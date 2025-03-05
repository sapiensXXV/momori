package com.momori.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class BinaryChoiceItem {
    private final String imageUrl;
    private final String description;
    private final int selectedCount; // 얼마나 선택되었는가를 나타내는 필드

    public BinaryChoiceItem(String imageUrl, String description) {
        this.imageUrl = imageUrl;
        this.description = description;
        this.selectedCount = 0;
    }
}
