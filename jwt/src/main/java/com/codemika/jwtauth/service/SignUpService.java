package com.codemika.jwtauth.service;

import com.codemika.jwtauth.dto.RqCreateUser;
import com.codemika.jwtauth.dto.RsCommonUser;
import com.codemika.jwtauth.entity.RoleEntity;
import com.codemika.jwtauth.entity.RoleUserEntity;
import com.codemika.jwtauth.entity.UserEntity;
import com.codemika.jwtauth.repository.RoleRepository;
import com.codemika.jwtauth.repository.RoleUserRepository;
import com.codemika.jwtauth.repository.UserRepository;
import com.codemika.jwtauth.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleUserRepository roleUserRepository;
    private final JwtUtil jwtUtil;

    public ResponseEntity<?> signUpUserService(RqCreateUser rq){
        if(userRepository.findByEmail(rq.getEmail()).isPresent()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("User with this email already exists");
        }

        UserEntity user  = new UserEntity()
                .setFirstName(rq.getName())
                .setLastName(rq.getSurname())
                .setEmail(rq.getEmail())
                .setPassword(rq.getPassword());

        user = userRepository.save(user);

        Optional<RoleEntity> role = roleRepository.findByRole("USER");
        if(!role.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("This role does not exist!");
        }
        RoleUserEntity roleUser = new RoleUserEntity()
                .setUser(user)
                 .setRole(role.get());
        roleUserRepository.save(roleUser);

        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("name", user.getFirstName());
        claims.put("surname", user.getLastName());
        claims.put("roles", "USER");

        RsCommonUser response = new RsCommonUser()
                .setId(user.getId())
                .setName(user.getFirstName())
                .setSurname(user.getLastName())
                .setEmail(user.getEmail())
                .setToken(jwtUtil.generateToken(claims));


        return ResponseEntity.ok(response);
    }
}

