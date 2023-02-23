package codemika.com.jwtauth.api;

import codemika.com.jwtauth.dto.CreateRole;
import codemika.com.jwtauth.dto.CreateUser;
import codemika.com.jwtauth.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SingInController {
    private final SignUpService service;


    @PostMapping("Sign-Up")
    private ResponseEntity<?> SignIn(@RequestBody CreateUser createUser, CreateRole createRole){
        return service.signUp(createUser, createRole);
    }
}
