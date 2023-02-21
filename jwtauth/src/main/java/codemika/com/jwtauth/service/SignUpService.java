package codemika.com.jwtauth.service;

import codemika.com.jwtauth.dto.CreateUser;
import codemika.com.jwtauth.dto.RsCommonUser;
import codemika.com.jwtauth.entity.UserEntity;
import codemika.com.jwtauth.repository.UserRepository;
import codemika.com.jwtauth.util.JWTUtil;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final UserRepository repository;
    private final JWTUtil jwtUtil;

    public ResponseEntity<?> signUp(CreateUser createUser) {
        UserEntity user = new UserEntity();
        user.setSurname(createUser.getSurname());
        user.setName(createUser.getName());
        user.setEmail(createUser.getEmail());
        user.setPassword(createUser.getPassword());

        user = repository.save(user);
        RsCommonUser rsCommonUser = new RsCommonUser()
                .setId(user.getId())
                .setName(user.getName())
                .setSurname(user.getSurname())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .setToken(jwtUtil.generateToken(Jwts.claims()));
        return ResponseEntity.ok(rsCommonUser);
    }
}
