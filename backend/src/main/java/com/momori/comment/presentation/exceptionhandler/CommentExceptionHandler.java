package com.momori.comment.presentation.exceptionhandler;


import com.momori.global.exception.CommentException;
import com.momori.global.exception.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Component
@RestControllerAdvice
public class CommentExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CommentException.class)
    public ErrorResult commentException(CommentException e) {
        log.info("CommentException 발생! message={}", e.getMessage());
        return new ErrorResult(e.getCode(), e.getMessage());
    }

}
