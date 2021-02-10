package org.sid.userservice.service;

import lombok.AllArgsConstructor;
import org.sid.userservice.entity.Formation;
import org.sid.userservice.entity.User;
import org.sid.userservice.repository.FormationRepository;
import org.sid.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class FormationServiceImpl implements FormationService {

    private final FormationRepository formationRepository;
    private final AccountService accountService;
    private final UserRepository userRepository;

    @Override
    public void saveUserFormation(Formation formation) {
        User user = accountService.getCurrentUser();
       formation.setUser(user);
       formationRepository.save(formation);
    }

    @Override
    public Collection<Formation> getCurrentUserFormations() {
        return accountService.getCurrentUser().getFormations();
    }

    @Override
    public Collection<Formation> getUserFormations(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(User::getFormations).orElse(null);
    }
}
