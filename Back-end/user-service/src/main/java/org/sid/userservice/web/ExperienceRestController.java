package org.sid.userservice.web;

import lombok.AllArgsConstructor;
import org.sid.userservice.entity.Experience;
import org.sid.userservice.service.ExperienceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/experience")
public class ExperienceRestController {

    private final ExperienceService experienceService;

    @GetMapping
    public List<Experience> getExperiences(){
        return (List<Experience>) experienceService.getCurrentUserExperiences();
    }

    @PostMapping
    public void addExperience(@RequestBody Experience experience){
        experienceService.saveUserExperience(experience);
    }

    @GetMapping("/{id}")
    public List<Experience> getUserExperiences(@PathVariable Long id){
        return (List<Experience>) experienceService.getUserExperiences(id);
    }

}
