package com.poolygo.quiz.resolver;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthenticateUser {
}
