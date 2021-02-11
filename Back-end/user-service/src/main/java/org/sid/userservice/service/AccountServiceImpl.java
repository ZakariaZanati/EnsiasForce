package org.sid.userservice.service;

import lombok.AllArgsConstructor;
import org.sid.userservice.entity.Role;
import org.sid.userservice.entity.User;
import org.sid.userservice.repository.RoleRepository;
import org.sid.userservice.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void addNewUser(User user) throws Exception {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null){
            throw new Exception(
                    "There is an account with that email address or username: "
                            +  user.getEmail());
        }
        userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        User user = userRepository.findByEmail(email);
        Role role = roleRepository.findByRoleName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public User getCurrentUser(){
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

}
