package codemika.com.jwtauth.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginUser {

    private String email;

    private String password;
}
