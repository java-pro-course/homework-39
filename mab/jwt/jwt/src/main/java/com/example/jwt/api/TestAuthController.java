package com.example.jwt.api;

import com.example.jwt.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestAuthController {

    private final JwtUtil jwtUtil;

    @GetMapping("validate-token")
    public ResponseEntity<?> testIsUserAuth(@RequestHeader("Authorization") String token) {
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok("token valid");
        }
        return ResponseEntity.ok("token INVALID");
    }

    @GetMapping("be-lucky")
    public ResponseEntity<?> beLuckyMethod(@RequestHeader("Authorization") String token) {
        Claims claimsparseToken = jwtUtil.getClaims(token);
        return ResponseEntity.ok(claimsparseToken.toString());
    }

    @GetMapping("my-profile")
    public ResponseEntity<?> myProfile(@RequestHeader("Authorization") String token) {
        Claims claimsparseToken = jwtUtil.getClaims(token);
        Long id = claimsparseToken.get("id", Long.class);
        String firstName = claimsparseToken.get("name", String.class);
        String lastName = claimsparseToken.get("surname", String.class);

        return ResponseEntity.ok(
                String.format(
                        "Hello from Mab to user #%s! Welcome %s, %s!",
                        id, firstName, lastName
                )
        );
    }
}


