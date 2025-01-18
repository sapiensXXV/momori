package com.poolygo.auth.exceptionhandler;


import com.poolygo.global.exception.ErrorResult;
import com.poolygo.global.exception.NoJwtTokenException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AuthControllerAdvice {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NoJwtTokenException.class)
    public ErrorResult noJwtTokenException(NoJwtTokenException e) {
        return new ErrorResult(e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(JwtException.class)
    public ErrorResult jwtTokenException(JwtException e) {
        return new ErrorResult(e.getMessage());
    }

}
