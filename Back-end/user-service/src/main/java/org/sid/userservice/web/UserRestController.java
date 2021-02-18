package org.sid.userservice.web;

import lombok.AllArgsConstructor;
import org.sid.userservice.dto.UserDetailsDto;
import org.sid.userservice.entity.User;
import org.sid.userservice.service.AccountService;
import org.sid.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserRestController {

    private final UserService userService;
    private final AccountService accountService;

    @GetMapping
    public User getCurrentUserDetails(){
        return accountService.getCurrentUser();
    }

    @GetMapping("/{id}")
    public UserDetailsDto getUserDetails(@PathVariable Long id){
        return userService.LoadUserDetailsById(id);
    }

    @PostMapping
    public void saveCurrentUserDetails(@RequestBody UserDetailsDto userDetailsDto){
        System.out.println(userDetailsDto);
        userService.saveUserDetails(userDetailsDto);
    }

    @PostMapping("/img")
    public ResponseEntity<byte[]> saveUserImage(@RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.saveUserImage(imageFile.getBytes()));
    }

}
