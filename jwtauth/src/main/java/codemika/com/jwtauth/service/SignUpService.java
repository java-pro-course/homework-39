package codemika.com.jwtauth.service;

import codemika.com.jwtauth.dto.CreateRole;
import codemika.com.jwtauth.dto.CreateUser;
import codemika.com.jwtauth.dto.RsCommonUser;
import codemika.com.jwtauth.entity.RoleEntity;
import codemika.com.jwtauth.entity.UserEntity;
import codemika.com.jwtauth.repository.RoleRepository;
import codemika.com.jwtauth.repository.UserRepository;
import codemika.com.jwtauth.util.JWTUtil;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignUpService {
    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final JWTUtil jwtUtil;

    public ResponseEntity<?> signUp(CreateUser createUser, CreateRole createRole) {
        log.info(createRole.toString());
        RoleEntity role = new RoleEntity();
        UserEntity user = new UserEntity();
        user.setSurname(createUser.getSurname());

        user.setName(createUser.getName());

        user.setEmail(createUser.getEmail());

        user.setPassword(createUser.getPassword());

        role.set_admin(createRole.isAdmin());

        role.set_investor(createRole.isInvestor());

        role.set_moderator(createRole.isModerator());

        role.set_user(true);

        role.setUser(user);

        role = roleRepository.save(role);

        user = repository.save(user);


        RsCommonUser rsCommonUser = new RsCommonUser()
                .setId(user.getId())
                .setName(user.getName())
                .setSurname(user.getSurname())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .setToken(jwtUtil.generateToken(Jwts.claims()))
                .set_user(role.is_user())
                .set_admin(role.is_admin())
                .set_investor(role.is_investor())
                .set_moderator(role.is_moderator());
        return ResponseEntity.ok(rsCommonUser);
    }
}
