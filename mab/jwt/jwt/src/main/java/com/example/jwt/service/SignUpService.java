package com.example.jwt.service;

import com.example.jwt.dto.RqCreateUser;
import com.example.jwt.dto.RsCommonUser;
import com.example.jwt.entity.UserEntity;
import com.example.jwt.repository.UserRepository;
import com.example.jwt.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final JwtUtil jwtUtil;

    private final UserRepository repository;

    public ResponseEntity<?> signUpUserService(RqCreateUser rq) {
        UserEntity user = new UserEntity()
                .setFirstName(rq.getName())
                .setLastName(rq.getSurname())
                .setEmail(rq.getEmail())
                .setPassword(rq.getPassword());


        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("name", user.getFirstName());
        claims.put("surname", user.getLastName());

        user = repository.save(user);

        RsCommonUser response = new RsCommonUser()
                .setId(user.getId())
                .setName(user.getFirstName())
                .setSurname(user.getLastName())
                .setEmail(user.getEmail())
                .setToken(
                        jwtUtil.generateToken(claims)
                );

        return ResponseEntity.ok(response);
    }

}
