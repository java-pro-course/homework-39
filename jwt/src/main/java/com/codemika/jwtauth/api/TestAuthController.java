package com.codemika.jwtauth.api;

import com.codemika.jwtauth.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestAuthController {

    private final JwtUtil jwtUtil;

    @GetMapping("validate-token")
    public ResponseEntity<?> testIsUserAuth(@RequestHeader("Authorisation") String token){
        if(jwtUtil.validateToken(token)){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("token valid!");
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("token INVALID");
    }

    @GetMapping("be-lucky")
    public ResponseEntity beLuckyMethod(@RequestHeader("Authorisation") String token){
        Claims parseToken = jwtUtil.getClaims(token);
        return ResponseEntity
                .ok(parseToken);
    }

    @GetMapping("my-profile")
    public ResponseEntity myProfile(@RequestHeader("Authorisation") String token){
        Claims parseToken = jwtUtil.getClaims(token);
        Long id = parseToken.get("id", Long.class);
        String name = parseToken.get("name", String.class);
        String surname = parseToken.get("surname", String.class);


        return ResponseEntity.ok(
                String.format("Welcome %s %s. Your id is #%s.", surname, name, id )
        );
    }
}
