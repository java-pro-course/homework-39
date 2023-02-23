package codemika.com.jwtauth.service;

import codemika.com.jwtauth.entity.UserEntity;
import codemika.com.jwtauth.repository.RoleRepository;
import codemika.com.jwtauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestRolesService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    public ResponseEntity<?> TestAdmin(Long id){
        Optional<UserEntity> user = userRepository.findById(id);
        if(!user.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The user does not exist!");
        }
        if (roleRepository.findByUser_id(id).get().is_admin()){
            return ResponseEntity.ok( "User by id: " + id + " is admin");
        }

        else {
            return ResponseEntity.ok("User by id: " + id + " is not admin");
        }

    }
    public ResponseEntity<?> TestModerator(Long id){
        Optional<UserEntity> user = userRepository.findById(id);
        if(!user.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The user does not exist!");
        }
        if (roleRepository.findByUser_id(id).get().is_moderator()){
            return ResponseEntity.ok( "User by id: " + id + " is moderator");
        }

        else {
            return ResponseEntity.ok("User by id: " + id + " is not moderator");
        }
}
    public ResponseEntity<?> TestInvestor(Long id){
        Optional<UserEntity> user = userRepository.findById(id);
        if(!user.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The user does not exist!");
        }
        if (roleRepository.findByUser_id(id).get().is_investor()){
            return ResponseEntity.ok( "User by id: " + id + " is investor");
        }

        else {
            return ResponseEntity.ok("User by id: " + id + " is not investor");
        }

    }

}

