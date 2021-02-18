package org.sid.userservice.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.sid.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("Attempt Authentication");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(email);
        System.out.println(password);
        UsernamePasswordAuthenticationToken
                authenticationToken = new UsernamePasswordAuthenticationToken(email,password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("Successful Authentication");
        User user = (User)authResult.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("mySecret1234");
        String jwtAccessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+48*60*60*1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles",user.getAuthorities().stream().map((Function<GrantedAuthority, Object>) GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        String jwtRefreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+48*60*60*1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);

        org.sid.userservice.entity.User currentUser = userRepository.findByEmail(user.getUsername());

        Map<String,String> idToken = new HashMap<>();
        idToken.put("authenticationToken",jwtAccessToken);
        idToken.put("refreshToken",jwtRefreshToken);
        idToken.put("email",user.getUsername());
        idToken.put("fullName",currentUser.getFullName());
        idToken.put("userType",currentUser.getRoles().stream().findFirst().get().getRoleName());
        idToken.put("completed",currentUser.getCompleted().toString());
        idToken.put("expiresAt",Instant.now().plusMillis(48*60*60*1000).toString());
        idToken.put("userID",currentUser.getId().toString());
        response.setContentType("application/json");
        final String origin = "http://localhost:4200";

        response.addHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers",
                "content-type, x-gwt-module-base, x-gwt-permutation, clientid, longpush");
        new ObjectMapper().writeValue(response.getOutputStream(),idToken);
    }

}
