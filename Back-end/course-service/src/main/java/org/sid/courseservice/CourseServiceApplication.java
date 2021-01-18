package org.sid.courseservice;

import org.sid.courseservice.entity.Course;
import org.sid.courseservice.entity.User;
import org.sid.courseservice.repository.CourseRepository;
import org.sid.courseservice.service.UserRestService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
@EnableFeignClients
public class CourseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserRestService userRestService,
            CourseRepository courseRepository,
            RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(Course.class);
        return args -> {
            User user = userRestService.getUserById(1L);
            System.out.println(user.toString());
        };

    }
}
