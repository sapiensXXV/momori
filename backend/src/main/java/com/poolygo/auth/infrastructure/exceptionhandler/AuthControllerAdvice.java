package com.poolygo.auth.infrastructure.exceptionhandler;


import com.poolygo.global.exception.AuthException;
import com.poolygo.global.exception.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AuthControllerAdvice {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthException.class)
    public ErrorResult noJwtTokenException(AuthException e) {
        return new ErrorResult(e.getCode(), e.getMessage());
    }

}
