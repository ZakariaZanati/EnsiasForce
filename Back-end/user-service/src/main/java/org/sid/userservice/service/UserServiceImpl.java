package org.sid.userservice.service;

import lombok.AllArgsConstructor;
import org.sid.userservice.dto.UserDetailsDto;
import org.sid.userservice.entity.User;
import org.sid.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountService accountService;

    public void saveUserDetails(UserDetailsDto userDetails){
        User user = accountService.getCurrentUser();
        user.setFullName(userDetails.getFullName());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        user.setStatus(userDetails.getStatus());
        user.setBirthDate(userDetails.getBirthDate());
        user.setCity(userDetails.getCity());
        user.setCountry(userDetails.getCountry());

        if (!user.getCompleted()){
            user.setCompleted(true);
        }
    }

    public UserDetailsDto LoadUserDetails(){
        return userRepository.getUserByEmail(accountService.getCurrentUser().getEmail());
    }

    public UserDetailsDto LoadUserDetailsById(Long id){
        return userRepository.getUserById(id);
    }

    public byte[] saveUserImage(byte[] image){
        User user = accountService.getCurrentUser();
        user.setImage(image);
        return image;
    }
}
