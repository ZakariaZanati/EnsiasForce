package org.sid.userservice.service;


import org.sid.userservice.entity.Skill;

import java.util.Collection;

public interface SkillService {

    void saveSkill(String skillName);
    void addSkillToUser(String skillName);
    Collection<Skill> getCurrentUserSkills();
    Collection<Skill> getUserSkills(Long id);
}
