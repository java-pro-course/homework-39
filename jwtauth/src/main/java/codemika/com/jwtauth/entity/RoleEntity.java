package codemika.com.jwtauth.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "xcrynge_jwt", name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.PERSIST)
    private UserEntity user;
    private boolean is_user;
    private boolean is_admin;
    private boolean is_moderator;
    private boolean is_investor;
}
