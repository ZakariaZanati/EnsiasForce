package org.sid.userservice.dto;

import lombok.Data;

import java.util.Date;

@Data
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
