package com.poolygo.quiz.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfo {

    private String userId;
    private String provider;
}
