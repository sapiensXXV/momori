package com.poolygo.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserAuthInfo {

    private String identifier;
    private String provider;
    private String name;
    private List<String> roles;

}
