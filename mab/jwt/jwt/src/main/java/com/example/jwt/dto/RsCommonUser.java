package com.example.jwt.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RsCommonUser {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String token;
}
