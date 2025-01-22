package com.poolygo.quiz.domain.repository;

import com.poolygo.quiz.domain.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizRepository extends MongoRepository<Quiz, String> {
}
