package com.codemika.jwtauth.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Accessors(chain = true)
@Table(schema = "jwt", name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "role")
    private List<RoleEntity> userRoles ;
}
