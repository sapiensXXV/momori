package com.poolygo.quiz.infrastructure;

import com.poolygo.quiz.domain.Quiz;
import com.poolygo.quiz.presentation.dto.response.QuizSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizRepository extends MongoRepository<Quiz, String> {
}
