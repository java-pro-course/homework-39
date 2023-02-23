package codemika.com.jwtauth.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;

@Data
@Entity
@Table(schema = "xcrynge_jwt", name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @Column(name = "first_name")
    private String name;
    @Column(name = "last_name")
    private String surname;
    private String password;
    @OneToOne(mappedBy = "user")
    private RoleEntity role;
}
