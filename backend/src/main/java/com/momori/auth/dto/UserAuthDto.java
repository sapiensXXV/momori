package com.momori.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserAuthDto {

    private String identifier;
    private String provider;
    private String name;
    private List<String> roles;

}
