package com.momori.quizdraft.infrastructure;

import com.momori.quizdraft.domain.QuizDraft;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuizDraftRepository extends MongoRepository<QuizDraft, String> {

    @Query("{ '_id': ?0, 'userInfo.identifier': ?1, 'userInfo.provider': ?2 }")
    Optional<QuizDraft> findByIdAndUserInfo(String draftId, String identifier, String provider);

    @Query("{ 'userInfo.identifier': ?0, 'userInfo.provider': ?1 }")
    List<QuizDraft> findAllByUserInfo(String identifier, String provider);

}
