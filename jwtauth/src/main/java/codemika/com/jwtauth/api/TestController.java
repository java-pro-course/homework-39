package codemika.com.jwtauth.api;

import codemika.com.jwtauth.util.JWTUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final JWTUtil jwtUtil;
    @GetMapping("test-user")
    public ResponseEntity<?> testIsUserAuth(@RequestHeader("Authorization") String token){
        if (jwtUtil.validateToken(token)) return ResponseEntity.ok("Token valid");

        return ResponseEntity.ok("Token INVALID");
    }

    @GetMapping("be-lucky")
    public ResponseEntity<?> beLucky(@RequestHeader("Authorization") String token){
        Claims parseToken = jwtUtil.getClaims(token);
        return ResponseEntity.ok(parseToken.toString());
    }
}
