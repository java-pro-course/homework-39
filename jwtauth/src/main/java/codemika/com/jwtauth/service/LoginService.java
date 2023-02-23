package codemika.com.jwtauth.service;

import codemika.com.jwtauth.dto.LoginUser;
import codemika.com.jwtauth.dto.RsCommonUser;
import codemika.com.jwtauth.entity.RoleEntity;
import codemika.com.jwtauth.entity.UserEntity;
import codemika.com.jwtauth.repository.RoleRepository;
import codemika.com.jwtauth.repository.UserRepository;
import codemika.com.jwtauth.util.JWTUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JWTUtil jwtUtil;

    public ResponseEntity<?> loginUser(LoginUser rq){
        Optional<UserEntity> user = userRepository.findByEmail(rq.getEmail());
        if(!user.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The user does not exist!");
        }
        if (!rq.getPassword().equals(user.get().getPassword())){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The password is incorrect!");
        }
        Optional<RoleEntity> role = roleRepository.findByUser_id(user.get().getId());

        Claims claims = Jwts.claims();
        claims.put("id", user.get().getId());
        claims.put("name", user.get().getName());
        claims.put("surname", user.get().getSurname());
        claims.put("isAdmin", role.get().is_admin());
        claims.put("isModerator", role.get().is_moderator());
        claims.put("isInvestor", role.get().is_investor());

        String newToken = jwtUtil.generateToken(claims);

        RsCommonUser response = new RsCommonUser()
                .setId(user.get().getId())
                .setName(user.get().getName())
                .setSurname(user.get().getSurname())
                .setEmail(user.get().getEmail())
                .setPassword(user.get().getPassword())
                .setToken(newToken)
                .set_user(role.get().is_user())
                .set_admin(role.get().is_admin())
                .set_investor(role.get().is_investor())
                .set_moderator(role.get().is_moderator());
        return ResponseEntity.ok(response);
    }
}