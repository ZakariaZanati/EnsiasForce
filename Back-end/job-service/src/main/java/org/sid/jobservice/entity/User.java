package org.sid.jobservice.entity;

import lombok.Data;

@Data
public class User {

    private Long id;

    private String fullName;

    private String email;

    private String password;

}
