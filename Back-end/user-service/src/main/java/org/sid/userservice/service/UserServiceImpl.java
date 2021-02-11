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
        User user = userRepository.getUserByEmail(accountService.getCurrentUser().getEmail());
        return UserDetailsDto.builder()
                .fullName(user.getFullName())
                .email(user.getEmail())
                .city(user.getCity())
                .country(user.getCountry())
                .birthDate(user.getBirthDate())
                .image(user.getImage())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .build();
    }

    public UserDetailsDto LoadUserDetailsById(Long id){
        User user = userRepository.getUserById(id);
        return UserDetailsDto.builder()
                .fullName(user.getFullName())
                .email(user.getEmail())
                .city(user.getCity())
                .country(user.getCountry())
                .birthDate(user.getBirthDate())
                .image(user.getImage())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .build();
    }

    public byte[] saveUserImage(byte[] image){
        User user = accountService.getCurrentUser();
        user.setImage(image);
        return image;
    }
}
