package org.sid.userservice.service;

import org.sid.userservice.entity.Experience;

import java.util.Collection;

public interface ExperienceService {

    void saveUserExperience(Experience experience);
    Collection<Experience> getCurrentUserExperiences();
    Collection<Experience> getUserExperiences(Long id);

}
