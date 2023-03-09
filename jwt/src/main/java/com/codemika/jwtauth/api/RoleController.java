package com.codemika.jwtauth.api;

import com.codemika.jwtauth.entity.RoleEntity;
import com.codemika.jwtauth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoleController {
    private final RoleRepository roleRepository;

    @PutMapping("create-role")
    public ResponseEntity createRole(@RequestParam String name){
        RoleEntity role = new RoleEntity()
                .setRole(name);
        roleRepository.save(role);
        return ResponseEntity.ok("Role created");
    }
}
