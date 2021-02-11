package org.sid.userservice.web;

import lombok.AllArgsConstructor;
import org.sid.userservice.entity.Skill;
import org.sid.userservice.service.SkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/skill")
public class SkillRestService {

    private final SkillService skillService;

    @GetMapping
    public List<Skill> getSkills(){
        return (List<Skill>) skillService.getCurrentUserSkills();
    }

    @GetMapping("/save")
    public void saveUserSkill(@RequestParam String skillName){
        skillService.saveSkill(skillName);
        skillService.addSkillToUser(skillName);
    }

    @GetMapping("/{id}")
    public List<Skill> getUserSkills(@PathVariable Long id){
        return (List<Skill>) skillService.getUserSkills(id);
    }
}
