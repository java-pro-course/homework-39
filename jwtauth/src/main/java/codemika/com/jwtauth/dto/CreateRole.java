package codemika.com.jwtauth.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateRole {
    private boolean admin;
    private boolean moderator;
    private boolean investor;
}
