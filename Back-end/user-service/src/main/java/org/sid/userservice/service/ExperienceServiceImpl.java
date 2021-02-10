package org.sid.userservice.service;

import lombok.AllArgsConstructor;
import org.sid.userservice.entity.Experience;
import org.sid.userservice.entity.User;
import org.sid.userservice.repository.ExperienceRepository;
import org.sid.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final AccountService accountService;
    private final UserRepository userRepository;

    @Override
    public void saveUserExperience(Experience experience) {
        User user = accountService.getCurrentUser();
        experience.setUser(user);
        experienceRepository.save(experience);
    }

    @Override
    public Collection<Experience> getCurrentUserExperiences() {
        User user = accountService.getCurrentUser();
        return user.getExperiences();
    }

    @Override
    public Collection<Experience> getUserExperiences(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(User::getExperiences).orElse(null);
    }
}
