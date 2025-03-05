package com.momori.quiz.domain.repository;

import com.momori.quiz.domain.Quiz;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface QuizRepository extends MongoRepository<Quiz, String> {

    @Query("{ 'title':  { $regex: ?0, $options:  'i' } }")
    List<Quiz> findByTitleMatching(String regex, Pageable pageable);
}
