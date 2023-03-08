package com.codemika.jwtauth.repository;

import com.codemika.jwtauth.entity.RoleUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleUserRepository extends JpaRepository<RoleUserEntity, Long> {

}
