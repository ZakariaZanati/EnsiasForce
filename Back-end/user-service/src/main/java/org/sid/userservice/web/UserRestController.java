package org.sid.userservice.web;

import lombok.AllArgsConstructor;
import org.sid.userservice.dto.UserDetailsDto;
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

    @GetMapping
    public UserDetailsDto getCurrentUserDetails(){
        return userService.LoadUserDetails();
    }

    @GetMapping("/{id}")
    public UserDetailsDto getUserDetails(@PathVariable Long id){
        return userService.LoadUserDetailsById(id);
    }

    @PostMapping
    public void saveCurrentUserDetails(@RequestBody UserDetailsDto userDetailsDto){
        userService.saveUserDetails(userDetailsDto);
    }

    @GetMapping("/img")
    public ResponseEntity<byte[]> saveUserImage(@RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.saveUserImage(imageFile.getBytes()));
    }

}
