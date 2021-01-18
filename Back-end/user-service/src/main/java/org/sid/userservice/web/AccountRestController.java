package org.sid.userservice.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.sid.userservice.dto.RoleUserForm;
import org.sid.userservice.entity.Role;
import org.sid.userservice.entity.User;
import org.sid.userservice.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AccountRestController {

    private final AccountService accountService;

    @PostMapping("/users")
    public User saveUser(@RequestBody User user){
        return accountService.addNewUser(user);
    }

    @PostMapping("/role")
    public Role saveRole(@RequestBody Role role){
        return accountService.addNewRole(role);
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return accountService.listUsers();
    }

    @PostMapping("/addRoleToUser")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm){
        accountService.addRoleToUser(roleUserForm.getEmail(),roleUserForm.getRoleName());
    }

    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authToken = request.getHeader("Authorization");
        if (authToken != null && authToken.startsWith("Bearer ")){
            try{
                String refreshToken = authToken.substring(7);
                Algorithm algorithm = Algorithm.HMAC256("mySecret1234");
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);
                String email = decodedJWT.getSubject();
                User user = accountService.loadUserByEmail(email);
                String jwtAccessToken = JWT.create()
                        .withSubject(email)
                        .withExpiresAt(new Date(System.currentTimeMillis()+5*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String,String> idToken = new HashMap<>();
                idToken.put("access-token",jwtAccessToken);
                idToken.put("refresh-token",refreshToken);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(),idToken);
            }catch (Exception e){
                throw e;
            }
        } else {
            throw new RuntimeException("Refresh token required !");
        }
    }

}
