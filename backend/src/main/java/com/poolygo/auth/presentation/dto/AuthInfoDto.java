package com.poolygo.auth.presentation.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AuthInfoDto {
    private String provider;
    private String name;
    private List<String> roles;
}
