package com.momori.comment.domain.repository;


import com.momori.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c where c.quizId=:quizId order by c.id desc")
    List<Comment> findByQuizId(@Param("quizId") String quizId);
}
