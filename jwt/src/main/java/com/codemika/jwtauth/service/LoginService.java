package com.codemika.jwtauth.service;

import com.codemika.jwtauth.dto.RqLoginUser;
import com.codemika.jwtauth.dto.RsCommonUser;
import com.codemika.jwtauth.entity.RoleEntity;
import com.codemika.jwtauth.entity.UserEntity;
import com.codemika.jwtauth.repository.RoleRepository;
import com.codemika.jwtauth.repository.RoleUserRepository;
import com.codemika.jwtauth.repository.UserRepository;
import com.codemika.jwtauth.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    private final RoleRepository roleRepository;
    private final RoleUserRepository roleUserRepository;
    private final UserRepository repository;
    private final JwtUtil jwt;

    public ResponseEntity<?> loginUser(RqLoginUser rq){
        Optional<UserEntity> user = repository.findByEmail(rq.getEmail());
        if(!user.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("fail!");
        }

        if(!rq.getPassword().equals(user.get().getPassword())){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("incorrect password!");
        }

        String roles = roleRepository.findById(roleUserRepository.findByUser_id(user.get().getId())).get().getRole();

        Claims claims = Jwts.claims();
        claims.put("id", user.get().getId());
        claims.put("name", user.get().getFirstName());
        claims.put("surname", user.get().getLastName());
        claims.put("roles", roles);

        String newToken = jwt.generateToken(claims);

        RsCommonUser rsUser = new RsCommonUser()
                .setId(user.get().getId())
                .setName(user.get().getFirstName())
                .setSurname(user.get().getLastName())
                .setEmail(user.get().getEmail())
                .setToken(newToken);

        return ResponseEntity.ok(rsUser);

    }



}
