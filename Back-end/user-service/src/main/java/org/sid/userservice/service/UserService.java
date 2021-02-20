package org.sid.userservice.service;

import org.sid.userservice.dto.UserDetailsDto;
import org.sid.userservice.dto.UsersDetailsResponse;

public interface UserService {

    void saveUserDetails(UserDetailsDto userDetails);
    UserDetailsDto LoadUserDetails();
    UserDetailsDto LoadUserDetailsById(Long id);
    byte[] saveUserImage(byte[] image);
    UsersDetailsResponse LoadUsersDetailsPage(int page, int size, String name, String location);
}
