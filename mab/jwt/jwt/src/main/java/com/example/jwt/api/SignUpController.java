package com.example.jwt.api;

import com.example.jwt.dto.RqCreateUser;
import com.example.jwt.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService service;

    @PostMapping("sign-up")
    public ResponseEntity<?> signUpUser(@RequestBody RqCreateUser rq) {
        return service.signUpUserService(rq);
    }
}
