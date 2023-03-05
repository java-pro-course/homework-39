package com.example.jwt.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RqCreateUser {
    private String surname;
    private String name;
    private String email;
    private String password;
}
