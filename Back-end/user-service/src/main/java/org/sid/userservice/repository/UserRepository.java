package org.sid.userservice.repository;

import org.sid.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}