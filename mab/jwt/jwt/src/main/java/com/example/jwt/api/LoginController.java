package com.example.jwt.api;

import com.example.jwt.dto.RqLoginUser;
import com.example.jwt.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService service;
    @PostMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody RqLoginUser rq){
        return service.loginUser(rq);
    }
}
