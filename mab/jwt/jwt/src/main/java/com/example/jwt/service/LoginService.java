package com.example.jwt.service;

import com.example.jwt.dto.RqLoginUser;
import com.example.jwt.dto.RsCommonUser;
import com.example.jwt.entity.UserEntity;
import com.example.jwt.repository.UserRepository;
import com.example.jwt.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    private final UserRepository repository;
    private final JwtUtil jwtUtil;

    public ResponseEntity<?> loginUser(RqLoginUser rq) {
        Optional<UserEntity> user = repository.findByEmail(rq.getEmail());
        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }

        log.info("Db pass: {}", user.get().getPassword());
        log.info("Request pas: {}", rq.getPassword());


        if (!Objects.equals(rq.getPassword(), user.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password is incorrect!");
        }

        Claims claims = Jwts.claims();
        claims.put("id", user.get().getId());
        claims.put("name", user.get().getFirstName());
        claims.put("surname", user.get().getLastName());

        String newToken = jwtUtil.generateToken(claims);

        RsCommonUser response = new RsCommonUser()
                .setId(user.get().getId())
                .setName(user.get().getFirstName())
                .setSurname(user.get().getLastName())
                .setEmail(user.get().getEmail())
                .setToken(newToken);

        return ResponseEntity.ok(response);
    }
}
