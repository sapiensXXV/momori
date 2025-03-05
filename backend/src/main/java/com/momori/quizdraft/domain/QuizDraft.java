package com.momori.quizdraft.domain;


import com.momori.quiz.domain.QuizType;
import com.momori.quiz.domain.UserInfo;
import jakarta.persistence.EntityListeners;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class QuizDraft {
    @Id
    private String id;

    @CreatedDate
    private LocalDateTime createdAt;

    private UserInfo userInfo;
    private String title;
    private String description;
    private QuizType type;
    private String thumbnailUrl;

    private List<? extends QuestionDraft> questions;
}
