package org.sid.userservice.service;

import org.sid.userservice.entity.Formation;

import java.util.Collection;

public interface FormationService {

    void saveUserFormation(Formation formation);
    Collection<Formation> getCurrentUserFormations();
    Collection<Formation> getUserFormations(Long id);

}
