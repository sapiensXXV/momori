package com.poolygo.comment.presentation.exceptionhandler;


import com.poolygo.global.exception.CommentException;
import com.poolygo.global.exception.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommentExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CommentException.class)
    public ErrorResult commentException(CommentException e) {
        return new ErrorResult(e.getMessage());
    }

}
