package com.poolygo.quiz.infrastructure;

import com.poolygo.quiz.domain.Quiz;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface QuizRepository extends MongoRepository<Quiz, String> {

    @Query("{ 'title':  { $regex: ?0, $option:  'i' } }")
    List<Quiz> findByTitleMatching(String regex, Pageable pageable);
}
