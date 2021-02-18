package org.sid.jobservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userID;

    private String name;

    private String description;

    private String enterprise;

    private String location;

    private String contractType;

    private String qualifications;

    private String salary;

    private String creationDate;

    private String expirationDate;

    private String link;

    @JsonIgnore
    @Transient
    private User user;
}
