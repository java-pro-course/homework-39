package com.codemika.jwtauth.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RqLoginUser {
    private String email;
    private String password;
}
