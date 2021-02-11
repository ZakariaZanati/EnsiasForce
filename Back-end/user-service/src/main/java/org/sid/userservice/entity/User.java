package org.sid.userservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String email;

    private String status;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Boolean completed;

    private String phoneNumber;

    private String city;

    private String country;

    private Date birthDate;

    @Lob
    private byte[] image;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private Collection<Experience> experiences = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private Collection<Formation> formations = new ArrayList<>();

    @ManyToMany
    private Collection<Skill> skills = new ArrayList<>();
}
