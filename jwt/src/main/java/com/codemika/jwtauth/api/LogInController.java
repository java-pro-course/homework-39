package com.codemika.jwtauth.api;

import com.codemika.jwtauth.dto.RqLoginUser;
import com.codemika.jwtauth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LogInController {
    private final LoginService service;

    @PostMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody RqLoginUser rq){
        return service.loginUser(rq);
    }
}
