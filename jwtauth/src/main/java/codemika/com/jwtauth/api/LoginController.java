package codemika.com.jwtauth.api;

import codemika.com.jwtauth.dto.LoginUser;
import codemika.com.jwtauth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class LoginController {
    private final LoginService loginService;

    @PostMapping("login-user")
    public ResponseEntity<?> loginUser(@RequestBody LoginUser user){
        return loginService.loginUser(user);
    }
}