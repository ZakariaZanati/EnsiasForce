package org.sid.courseservice.service;

import org.sid.courseservice.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserRestService {

    @GetMapping(path = "/users/{id}")
    User getUserById(@PathVariable Long id);
}
