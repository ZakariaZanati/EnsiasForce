package org.sid.courseservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.springframework.lang.Nullable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
    
    private LocalDateTime dateTime;

    private String duration;

    private String time;

    private String location;

    private String link;

    private String category;
    
    @Nullable
    //@Column(length = 1048570)
    @Lob
    private byte[] imageBytes;

    private Long publisherID;

    @Transient
    private User publisher;
}
