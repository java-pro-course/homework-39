package codemika.com.jwtauth.dto;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;

@Data
@Accessors(chain = true)
public class RsCommonUser {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String token;
}
