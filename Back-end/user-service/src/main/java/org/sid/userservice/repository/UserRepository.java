package org.sid.userservice.repository;

import org.sid.userservice.dto.UserDetailsDto;
import org.sid.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
    UserDetailsDto getUserByEmail(String email);
    UserDetailsDto getUserById(Long id);

}
