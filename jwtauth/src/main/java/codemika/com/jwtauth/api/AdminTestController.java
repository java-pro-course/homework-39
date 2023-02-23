package codemika.com.jwtauth.api;

import codemika.com.jwtauth.service.TestRolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminTestController {
    private final TestRolesService test;
    @PostMapping("is-admin")
    private ResponseEntity<?> checkAdmin(@RequestBody Long id){
        return ResponseEntity.ok(test.TestAdmin(id));
    }
    @PostMapping("is-moderator")
    private ResponseEntity<?> checkModerator(@RequestBody Long id){
        return ResponseEntity.ok(test.TestModerator(id));
    }
    @PostMapping("is-investor")
    private ResponseEntity<?> checkInvestor(@RequestBody Long id){
        return ResponseEntity.ok(test.TestInvestor(id));
    }
}
