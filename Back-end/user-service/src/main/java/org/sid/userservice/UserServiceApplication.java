package org.sid.userservice;

import org.sid.userservice.entity.Role;
import org.sid.userservice.entity.User;
import org.sid.userservice.repository.UserRepository;
import org.sid.userservice.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@EnableFeignClients
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AccountService accountService, RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(User.class);
        return args -> {
            //userRepository.save(new User(null,"Zakaria Zanati","zakaria@gmail.com","123456"));
            //userRepository.save(new User(null,"Mohamed Alaoui","mohamed@gmail.com","123456"));

/*
            accountService.addNewRole(new Role(null,"STUDENT"));
            accountService.addNewRole(new Role(null,"GRADUATE"));

            accountService.addNewUser(new User(null,"Zakaria Zanati","zakaria@gmail.com","123456",new ArrayList<>()));
            accountService.addNewUser(new User(null,"Mohamed Alaoui","mohamed@gmail.com","123456",new ArrayList<>()));

            accountService.addRoleToUser("zakaria@gmail.com","STUDENT");
            accountService.addRoleToUser("mohamed@gmail.com","GRADUATE");

 */
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
