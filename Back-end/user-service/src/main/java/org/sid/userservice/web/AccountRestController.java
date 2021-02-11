package org.sid.userservice.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.sid.userservice.dto.RefreshTokenRequest;
import org.sid.userservice.dto.RoleUserForm;
import org.sid.userservice.entity.Role;
import org.sid.userservice.entity.User;
import org.sid.userservice.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin
public class AccountRestController {

    private final AccountService accountService;

    @PostMapping("/signup")
    public ResponseEntity<String> saveUser(@RequestBody User user){

        try {
            System.out.println(user);
            accountService.addNewUser(user);
            return new ResponseEntity<>("User Registration Successful", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Registration failed",HttpStatus.BAD_REQUEST);
        }
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
    public String addRoleToUser(@RequestBody RoleUserForm roleUserForm){
        accountService.addRoleToUser(roleUserForm.getEmail(),roleUserForm.getRoleName());
        return "Role added successfully";
    }

    @PostMapping("/refreshToken")
    public void refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest,HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authToken = refreshTokenRequest.getRefreshToken();

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

                idToken.put("authenticationToken",jwtAccessToken);
                idToken.put("refreshToken",refreshToken);
                idToken.put("email",user.getEmail());
                idToken.put("fullName",user.getFullName());
                idToken.put("userType",user.getRoles().stream().findFirst().toString());
                idToken.put("completed",user.getCompleted().toString());
                idToken.put("expiresAt", Instant.now().plusMillis(5*60*1000).toString());
                response.setContentType("application/json");
                final String origin = "http://localhost:4200";

                response.addHeader("Access-Control-Allow-Origin", origin);
                response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
                response.setHeader("Access-Control-Allow-Credentials", "true");
                response.setHeader("Access-Control-Allow-Headers",
                        "content-type, x-gwt-module-base, x-gwt-permutation, clientid, longpush");
                new ObjectMapper().writeValue(response.getOutputStream(),idToken);
            }catch (Exception e){
                throw e;
            }
        } else {
            throw new RuntimeException("Refresh token required !");
        }
    }

}
