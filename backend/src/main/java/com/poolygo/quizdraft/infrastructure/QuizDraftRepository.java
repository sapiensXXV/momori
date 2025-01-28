package com.poolygo.quizdraft.infrastructure;

import com.poolygo.quizdraft.domain.QuizDraft;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizDraftRepository extends MongoRepository<QuizDraft, String> {
}
