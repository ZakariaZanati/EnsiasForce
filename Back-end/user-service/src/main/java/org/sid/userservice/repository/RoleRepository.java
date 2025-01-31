package org.sid.userservice.repository;

import org.sid.userservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByRoleName(String roleName);
}
