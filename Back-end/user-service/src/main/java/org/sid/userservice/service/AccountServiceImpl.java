package org.sid.userservice.service;

import lombok.AllArgsConstructor;
import org.sid.userservice.entity.Role;
import org.sid.userservice.entity.User;
import org.sid.userservice.repository.RoleRepository;
import org.sid.userservice.repository.UserRepository;
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
    public User addNewUser(User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
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

    /*
    @Override
    public AuthenticationResponse loginUser(LoginRequest loginRequest) {
        System.out.println("fdfffffffffffffffffffffffff");
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtAuthenticationFilter.generateToken(authentication);
            System.out.println(token);
            return AuthenticationResponse.builder()
                    .authenticationToken(token)
                    .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                    .expiresAt(Instant.now().plusMillis(5*60*1000))
                    .email(loginRequest.getEmail())
                    .fullName(getCurrentUser().getFullName())
                    .build();
        }catch (Exception e){
            System.out.println("Error");
        }
        return null;

    }

     */

    /*
    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws Exception {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtAuthenticationFilter.generateTokenWithEmail(refreshTokenRequest.getEmail());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(5*60*1000))
                .email(refreshTokenRequest.getEmail())
                .build();
    }

     */
    /*
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(principal.getUsername());
    }

     */
}
