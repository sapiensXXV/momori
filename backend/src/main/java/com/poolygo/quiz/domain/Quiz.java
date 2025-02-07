package com.poolygo.quiz.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "quiz")
@Getter
@Builder
public class Quiz {
    @Id
    @Field(name = "_id")
    private String id;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;

    private UserInfo userInfo;
    private String title;
    private String description;
    private QuizType type;
    private String thumbnailUrl;
    private int views;
    private int tries;
    private int likes;

    private List<? extends Question> questions;

    public void addView() {
        this.views++;
    }

    public void addTries() {
        this.tries++;
    }

    public void addLikes() {
        this.likes++;
    }
}
