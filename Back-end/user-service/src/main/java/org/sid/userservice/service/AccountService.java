package org.sid.userservice.service;

import org.sid.userservice.entity.Role;
import org.sid.userservice.entity.User;

import java.util.List;

public interface AccountService {

    void addNewUser(User user) throws Exception;
    Role addNewRole(Role role);
    void addRoleToUser(String email, String roleName);
    User loadUserByEmail(String email);
    List<User> listUsers();
}
