package codemika.com.jwtauth.api;

import codemika.com.jwtauth.util.JWTUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final JWTUtil jwtUtil;
    @PostMapping("test-user")
    public ResponseEntity<?> testIsUserAuth(@RequestBody String token){
        if (jwtUtil.validateToken(token)) return ResponseEntity.ok("Token VALID");

        return ResponseEntity.ok("Token INVALID");
    }

    @PostMapping("be-lucky")
    public ResponseEntity<?> beLucky(@RequestBody String token){
        Claims parseToken = jwtUtil.getClaims(token);
        return ResponseEntity.ok(parseToken.toString());
    }
}
