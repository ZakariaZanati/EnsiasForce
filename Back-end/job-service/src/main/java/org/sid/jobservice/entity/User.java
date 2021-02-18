package org.sid.jobservice.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private String fullName;

    private String email;

    private String status;

    private String phoneNumber;

    private String city;

    private String country;

    private Date birthDate;

    private byte[] image;

}
