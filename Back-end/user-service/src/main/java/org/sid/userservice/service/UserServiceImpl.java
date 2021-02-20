package org.sid.userservice.service;

import lombok.AllArgsConstructor;
import org.sid.userservice.dto.UserDetailsDto;
import org.sid.userservice.dto.UsersDetailsResponse;
import org.sid.userservice.entity.User;
import org.sid.userservice.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

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

    public UsersDetailsResponse LoadUsersDetailsPage(int page, int size, String fullname, String location){
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users;

        if (fullname.equals("")){
            if (!location.equals("")){
                users = userRepository.findByCityOrCountry(location,location,pageable);
            }
            else {
                users = userRepository.findAll(pageable);
            }
        }
        else {
            users = userRepository.findByFullNameContains(pageable,fullname);
        }

        List<User> usersResponse = users.stream().collect(Collectors.toList());
        return new UsersDetailsResponse(usersResponse,users.getTotalPages(),users.getNumber(),users.getSize());
    }
}
