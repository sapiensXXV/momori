package com.poolygo.quiztemp.domain;


import com.poolygo.quiz.domain.Question;
import com.poolygo.quiz.domain.QuizType;
import com.poolygo.quiz.domain.UserInfo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Getter
@Builder
public class QuizTemp {
    @Id
    private String id;

    @CreatedDate
    private LocalDateTime createdAt;

    private UserInfo userInfo;
    private String title;
    private String description;
    private QuizType type;

    private List<? extends Question> questions;
}
