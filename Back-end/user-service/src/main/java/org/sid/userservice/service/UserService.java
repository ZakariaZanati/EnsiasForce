package org.sid.userservice.service;

import org.sid.userservice.dto.UserDetailsDto;

public interface UserService {

    void saveUserDetails(UserDetailsDto userDetails);
    UserDetailsDto LoadUserDetails();
    UserDetailsDto LoadUserDetailsById(Long id);
    byte[] saveUserImage(byte[] image);
}
