package org.sid.jobservice;

import org.sid.jobservice.entity.Job;
import org.sid.jobservice.entity.User;
import org.sid.jobservice.repository.JobRepository;
import org.sid.jobservice.service.UserRestService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
@EnableFeignClients
public class JobserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobserviceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(JobRepository jobRepository, RepositoryRestConfiguration restConfiguration, UserRestService userRestService){
        restConfiguration.exposeIdsFor(Job.class);
        return args -> {
          //jobRepository.save(new Job(null,"Java/Jee developer","descr"));
          //jobRepository.save(new Job(null,"Data scientist","description"));
            User user = userRestService.getUserById(1L);
            System.out.println(user.toString());
        };
    }
}
