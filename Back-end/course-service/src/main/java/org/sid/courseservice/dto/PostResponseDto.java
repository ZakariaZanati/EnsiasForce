package org.sid.courseservice.dto;

import java.time.Instant;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {
	
	private Long id;
	private String description;
    private String publisherFullName;
    private Integer likeCount;
    private Integer commentCount;
    private byte[] profileImage;
    private boolean liked;
    private Instant dateCreation;
    private String duration;
    private byte[] image;
}
