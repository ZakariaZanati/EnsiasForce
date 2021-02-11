package org.sid.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {

    private String fullName;

    private String email;

    private String status;

    private String phoneNumber;

    private String city;

    private String country;

    private Date birthDate;

    private byte[] image;

}
