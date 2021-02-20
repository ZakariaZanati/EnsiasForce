package org.sid.userservice.repository;

import org.sid.userservice.dto.UserDetailsDto;
import org.sid.userservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
    User getUserByEmail(String email);
    User getUserById(Long id);
    Page<User> findByCityOrCountry(String city, String country, Pageable pageable);
    Page<User> findByFullNameContains(Pageable pageable, String fullName);
}
