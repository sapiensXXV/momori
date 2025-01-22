package com.poolygo.quiz.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfo {

    private String userId;
    private String provider;
}
