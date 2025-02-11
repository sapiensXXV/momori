package com.poolygo.comment.application;


import com.poolygo.auth.dto.UserAuthDto;
import com.poolygo.comment.domain.Comment;
import com.poolygo.comment.domain.factory.CommentFactory;
import com.poolygo.comment.domain.mapper.CommentMapper;
import com.poolygo.comment.domain.repository.CommentQueryRepository;
import com.poolygo.comment.domain.repository.CommentRepository;
import com.poolygo.comment.presentation.dto.CommentCreateRequest;
import com.poolygo.comment.presentation.dto.CommentCreateResponse;
import com.poolygo.comment.presentation.dto.CommentDetailResponse;
import com.poolygo.global.exception.AuthException;
import com.poolygo.global.exception.ExceptionCode;
import com.poolygo.global.exception.QuizException;
import com.poolygo.quiz.domain.Quiz;
import com.poolygo.quiz.domain.repository.QuizRepository;
import com.poolygo.user.domain.User;
import com.poolygo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PasswordEncoder passwordEncoder;
    private final CommentFactory commentFactory;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    private final CommentQueryRepository commentQueryRepository;

    public List<CommentDetailResponse> findComments(String quizId, long lastId, int size) {
        return commentQueryRepository.commentList(quizId, lastId, size);
    }

    /**
     * 익명 댓글을 생성하는 메서드
     */
    public CommentCreateResponse createAnonymousComment(
        final String quizId,
        final CommentCreateRequest request
    ) {
        Comment comment = commentFactory.createAnonymousComment(quizId, createPasswordEncryptRequest(request));

        commentRepository.save(comment);
        return commentMapper.toCommentCreateResponse(comment);
    }

    /**
     * 사용자 댓글을 생성하는 메서드
     */
    public CommentCreateResponse createUserComment(
        final String quizId,
        final CommentCreateRequest request,
        UserAuthDto auth
    ) {
        User findUser = userRepository.findByIdentifier(auth.getIdentifier())
            .orElseThrow(() -> new AuthException(ExceptionCode.INVALID_USER_ID));

        Quiz findQuiz = quizRepository.findById(quizId)
            .orElseThrow((() -> new QuizException(ExceptionCode.INVALID_QUIZ_ID)));

        isQuizMaker(findUser, findQuiz);

        Comment comment = commentFactory.createUserComment(quizId, createPasswordEncryptRequest(request), findUser, isQuizMaker(findUser, findQuiz));
        commentRepository.save(comment);
        return commentMapper.toCommentCreateResponse(comment);
    }

    private CommentCreateRequest createPasswordEncryptRequest(final CommentCreateRequest request) {
        return new CommentCreateRequest(
            request.getName(),
            passwordEncoder.encode(request.getPassword()), // 암호화된 패스워드
            request.getContent()
        );
    }

    private boolean isQuizMaker(User findUser, Quiz findQuiz) {
        String identifier1 = findUser.getIdentifier();
        String provider1 = findUser.getProvider().name();
        String identifier2 = findQuiz.getUserInfo().getIdentifier();
        String provider2 = findQuiz.getUserInfo().getProvider();

        return identifier1.equals(identifier2) && provider1.equalsIgnoreCase(provider2);
    }

}
