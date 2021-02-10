package org.sid.userservice.service;

import lombok.AllArgsConstructor;
import org.sid.userservice.entity.Skill;
import org.sid.userservice.entity.User;
import org.sid.userservice.repository.SkillRepository;
import org.sid.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final AccountService accountService;
    private final UserRepository userRepository;

    @Override
    public void saveSkill(String skillName) {
        Optional<Skill> skill = skillRepository.findBySkillName(skillName);
        if (!skill.isPresent()){
            skillRepository.save(new Skill(null,skillName));
        }
    }

    @Override
    public void addSkillToUser(String skillName) {
        User user = accountService.getCurrentUser();
        user.getSkills().add(skillRepository.findBySkillName(skillName).get());
    }


    @Override
    public Collection<Skill> getCurrentUserSkills() {
        User user = accountService.getCurrentUser();
        return user.getSkills();
    }

    @Override
    public Collection<Skill> getUserSkills(Long id) {
        User user = userRepository.findById(id).get();
        return user.getSkills();
    }
}
