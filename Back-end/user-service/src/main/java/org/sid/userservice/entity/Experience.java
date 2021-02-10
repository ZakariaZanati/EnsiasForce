package org.sid.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String enterprise;

    private String position;

    private String location;

    private String startDate;

    private String endDate;

    @Lob
    private byte[] image;

    @JsonIgnore
    @ManyToOne
    private User user;
}
